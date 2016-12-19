package com.barclays.indiacp.cordapp.contract

import net.corda.contracts.ICommercialPaperState
import net.corda.contracts.asset.sumCashBy
import net.corda.contracts.clause.AbstractIssue
import net.corda.core.contracts.*
import net.corda.core.contracts.clauses.AnyComposition
import net.corda.core.contracts.clauses.Clause
import net.corda.core.contracts.clauses.GroupClauseVerifier
import net.corda.core.contracts.clauses.verifyClause
import net.corda.core.node.services.VaultService
import net.corda.core.random63BitValue
import net.corda.core.schemas.MappedSchema
import net.corda.core.schemas.PersistentState
import net.corda.core.schemas.QueryableState
import net.corda.core.transactions.TransactionBuilder
import net.corda.core.utilities.Emoji
import com.barclays.indiacp.cordapp.schemas.IndiaCommercialPaperSchemaV1
import net.corda.core.crypto.*
import java.time.Instant
import java.util.*

/**
 * This is an ultra-trivial implementation of commercial paper, which is essentially a simpler version of a corporate
 * bond. It can be seen as a company-specific currency. A company issues CP with a particular face value, say $100,
 * but sells it for less, say $90. The paper can be redeemed for cash at a given date in the future. Thus this example
 * would have a 10% interest rate with a single repayment. Commercial paper is often rolled over (the maturity date
 * is adjusted as if the paper was redeemed and immediately repurchased, but without having to front the cash).
 *
 * This contract is not intended to realistically model CP. It is here only to act as a next step up above cash in
 * the prototyping phase. It is thus very incomplete.
 *
 * Open issues:
 *  - In this model, you cannot merge or split CP. Can you do this normally? We could model CP as a specialised form
 *    of cash, or reuse some of the cash code? Waiting on response from Ayoub and Rajar about whether CP can always
 *    be split/merged or only in secondary markets. Even if current systems can't do this, would it be a desirable
 *    feature to have anyway?
 *  - The funding steps of CP is totally ignored in this model.
 *  - No attention is paid to the existing roles of custodians, funding banks, etc.
 *  - There are regional variations on the CP concept, for instance, American CP requires a special "CUSIP number"
 *    which may need to be tracked. That, in turn, requires validation logic (there is a bean validator that knows how
 *    to do this in the Apache BVal project).
 */

val CP_PROGRAM_ID = IndiaCommercialPaper()

// TODO: Generalise the notion of an owned instrument into a superclass/supercontract. Consider composition vs inheritance.
class IndiaCommercialPaper : Contract {
    // TODO: should reference the content of the legal agreement, not its URI
    override val legalContractReference: SecureHash = SecureHash.sha256("https://en.wikipedia.org/wiki/Commercial_paper")

    data class Terms(
            val asset: Issued<Currency>,
            val maturityDate: Instant
    )

    /** An ISIN is generated by the depository (NSDL). It can be embedded in a tx. */
    data class ISIN(val isin: String) : CommandData

    override fun verify(tx: TransactionForContract) = verifyClause(tx, IndiaCommercialPaper.Clauses.Group(), tx.commands.select<IndiaCommercialPaper.Commands>())

    data class State(
            val issuance: PartyAndReference,
            override val owner: CompositeKey,
            val faceValue: Amount<Issued<Currency>>,
            val maturityDate: Instant,
            val version: Integer,
            val isin: String
    ) : OwnableState, QueryableState, ICommercialPaperState {
        override val contract = net.corda.contracts.CP_PROGRAM_ID
        override val participants: List<CompositeKey>
            get() = listOf(owner)

        val token: Issued<IndiaCommercialPaper.Terms>
            get() = Issued(issuance, IndiaCommercialPaper.Terms(faceValue.token, maturityDate))

        override fun withNewOwner(newOwner: CompositeKey) = Pair(Commands.Move(), copy(owner = newOwner))
        override fun toString() = "${Emoji.newspaper}CommercialPaper(of $faceValue redeemable on $maturityDate by '$issuance', owned by ${owner.toString()})"

        // Although kotlin is smart enough not to need these, as we are using the ICommercialPaperState, we need to declare them explicitly for use later,
        override fun withOwner(newOwner: CompositeKey): ICommercialPaperState = copy(owner = newOwner)

        override fun withIssuance(newIssuance: PartyAndReference): ICommercialPaperState = copy(issuance = newIssuance)
        override fun withFaceValue(newFaceValue: Amount<Issued<Currency>>): ICommercialPaperState = copy(faceValue = newFaceValue)
        override fun withMaturityDate(newMaturityDate: Instant): ICommercialPaperState = copy(maturityDate = newMaturityDate)

        /** Object Relational Mapping support. */
        override fun supportedSchemas(): Iterable<MappedSchema> = listOf(IndiaCommercialPaperSchemaV1)

        /** Object Relational Mapping support. */
        override fun generateMappedObject(schema: MappedSchema): PersistentState {
            return when (schema) {
                is IndiaCommercialPaperSchemaV1 -> IndiaCommercialPaperSchemaV1.PersistentIndiaCommericalPaperState(
                        issuanceParty = this.issuance.party.owningKey.toBase58String(),
                        issuanceRef = this.issuance.reference.bytes,
                        owner = this.owner.toBase58String(),
                        maturity = this.maturityDate,
                        faceValue = this.faceValue.quantity,
                        currency = this.faceValue.token.product.currencyCode,
                        faceValueIssuerParty = this.faceValue.token.issuer.party.owningKey.toBase58String(),
                        faceValueIssuerRef = this.faceValue.token.issuer.reference.bytes,
                        version = this.version,
                        isin = this.isin
                )
                else -> throw IllegalArgumentException("Unrecognised schema $schema")
            }
        }

        fun generateISIN(ptx: TransactionBuilder, oldState: StateAndRef<*>, isin: String) {
            IndiaCommercialPaper().generateISIN(ptx, StateAndRef(TransactionState(this, oldState.state.notary), oldState.ref), isin)
        }


    }

    interface Clauses {
        class Group : GroupClauseVerifier<IndiaCommercialPaper.State, IndiaCommercialPaper.Commands, Issued<IndiaCommercialPaper.Terms>>(
                AnyComposition(
                        IndiaCommercialPaper.Clauses.Redeem(),
                        IndiaCommercialPaper.Clauses.Move(),
                        IndiaCommercialPaper.Clauses.Issue(),
                        IndiaCommercialPaper.Clauses.ISINGeneration())) {
            override fun groupStates(tx: TransactionForContract): List<TransactionForContract.InOutGroup<IndiaCommercialPaper.State, Issued<IndiaCommercialPaper.Terms>>>
                    = tx.groupStates<IndiaCommercialPaper.State, Issued<IndiaCommercialPaper.Terms>> { it.token }
        }

        class Issue : AbstractIssue<IndiaCommercialPaper.State, IndiaCommercialPaper.Commands, IndiaCommercialPaper.Terms>(
                { map { Amount(it.faceValue.quantity, it.token) }.sumOrThrow() },
                { token -> map { Amount(it.faceValue.quantity, it.token) }.sumOrZero(token) }) {
            override val requiredCommands: Set<Class<out CommandData>> = setOf(IndiaCommercialPaper.Commands.Issue::class.java)

            override fun verify(tx: TransactionForContract,
                                inputs: List<IndiaCommercialPaper.State>,
                                outputs: List<IndiaCommercialPaper.State>,
                                commands: List<AuthenticatedObject<IndiaCommercialPaper.Commands>>,
                                groupingKey: Issued<IndiaCommercialPaper.Terms>?): Set<IndiaCommercialPaper.Commands> {
                val consumedCommands = super.verify(tx, inputs, outputs, commands, groupingKey)
                commands.requireSingleCommand<IndiaCommercialPaper.Commands.Issue>()
                val timestamp = tx.timestamp
                val time = timestamp?.before ?: throw IllegalArgumentException("Issuances must be timestamped")

                require(outputs.all { time < it.maturityDate }) { "maturity date is not in the past" }

                return consumedCommands
            }
        }

        class Move: Clause<IndiaCommercialPaper.State, IndiaCommercialPaper.Commands, Issued<IndiaCommercialPaper.Terms>>() {
            override val requiredCommands: Set<Class<out CommandData>> = setOf(IndiaCommercialPaper.Commands.Move::class.java)

            override fun verify(tx: TransactionForContract,
                                inputs: List<IndiaCommercialPaper.State>,
                                outputs: List<IndiaCommercialPaper.State>,
                                commands: List<AuthenticatedObject<IndiaCommercialPaper.Commands>>,
                                groupingKey: Issued<IndiaCommercialPaper.Terms>?): Set<IndiaCommercialPaper.Commands> {
                val command = commands.requireSingleCommand<IndiaCommercialPaper.Commands.Move>()
                val input = inputs.single()
                requireThat {
                    "the transaction is signed by the owner of the CP" by (input.owner in command.signers)
                    "the state is propagated" by (outputs.size == 1)
                    // Don't need to check anything else, as if outputs.size == 1 then the output is equal to
                    // the input ignoring the owner field due to the grouping.
                }
                return setOf(command.value)
            }
        }

        class Redeem(): Clause<IndiaCommercialPaper.State, IndiaCommercialPaper.Commands, Issued<IndiaCommercialPaper.Terms>>() {
            override val requiredCommands: Set<Class<out CommandData>> = setOf(IndiaCommercialPaper.Commands.Redeem::class.java)

            override fun verify(tx: TransactionForContract,
                                inputs: List<IndiaCommercialPaper.State>,
                                outputs: List<IndiaCommercialPaper.State>,
                                commands: List<AuthenticatedObject<IndiaCommercialPaper.Commands>>,
                                groupingKey: Issued<IndiaCommercialPaper.Terms>?): Set<IndiaCommercialPaper.Commands> {
                // TODO: This should filter commands down to those with compatible subjects (underlying product and maturity date)
                // before requiring a single command
                val command = commands.requireSingleCommand<IndiaCommercialPaper.Commands.Redeem>()
                val timestamp = tx.timestamp

                val input = inputs.single()
                val received = tx.outputs.sumCashBy(input.owner)
                val time = timestamp?.after ?: throw IllegalArgumentException("Redemptions must be timestamped")
                requireThat {
                    "the paper must have matured" by (time >= input.maturityDate)
                    "the received amount equals the face value" by (received == input.faceValue)
                    "the paper must be destroyed" by outputs.isEmpty()
                    "the transaction is signed by the owner of the CP" by (input.owner in command.signers)
                }

                return setOf(command.value)
            }

        }

        class ISINGeneration: Clause<IndiaCommercialPaper.State, IndiaCommercialPaper.Commands, Issued<IndiaCommercialPaper.Terms>>() {
            override val requiredCommands: Set<Class<out CommandData>> = setOf(IndiaCommercialPaper.Commands.ISINGeneration::class.java)

            override fun verify(tx: TransactionForContract,
                                inputs: List<IndiaCommercialPaper.State>,
                                outputs: List<IndiaCommercialPaper.State>,
                                commands: List<AuthenticatedObject<IndiaCommercialPaper.Commands>>,
                                groupingKey: Issued<IndiaCommercialPaper.Terms>?): Set<IndiaCommercialPaper.Commands> {
                val command = commands.requireSingleCommand<IndiaCommercialPaper.Commands.ISINGeneration>()
                val input = inputs.single()
                val timestamp = tx.timestamp
                val time = timestamp?.before ?: throw IllegalArgumentException("ISIN Generation must be timestamped")
                //TODO: attach NSDL Documents for verification
                requireThat { "the transaction is signed by the owner of the CP" by (input.owner in command.signers)}

                return setOf(command.value)
            }
        }


    }

    interface Commands : CommandData {
        data class Move(override val contractHash: SecureHash? = null) : FungibleAsset.Commands.Move, IndiaCommercialPaper.Commands
        class Redeem : TypeOnlyCommandData(), IndiaCommercialPaper.Commands
        data class Issue(override val nonce: Long = random63BitValue()) : IssueCommand, IndiaCommercialPaper.Commands
        class ISINGeneration(isin: String) : IndiaCommercialPaper.Commands
    }

    /**
     * Returns a transaction that issues commercial paper, owned by the issuing parties key. Does not update
     * an existing transaction because you aren't able to issue multiple pieces of CP in a single transaction
     * at the moment: this restriction is not fundamental and may be lifted later.
     */
    fun generateIssue(issuance: PartyAndReference, faceValue: Amount<Issued<Currency>>, maturityDate: Instant, notary: Party): TransactionBuilder {
        val state = TransactionState(IndiaCommercialPaper.State(issuance, issuance.party.owningKey, faceValue, maturityDate, Integer(0), ""), notary)
        return TransactionType.General.Builder(notary = notary).withItems(state, Command(IndiaCommercialPaper.Commands.Issue(), issuance.party.owningKey))
    }

    /**
     * Updates the given partial transaction with an input/output/command to reassign ownership of the paper.
     */
    fun generateMove(tx: TransactionBuilder, paper: StateAndRef<IndiaCommercialPaper.State>, newOwner: CompositeKey) {
        tx.addInputState(paper)
        tx.addOutputState(TransactionState(paper.state.data.copy(owner = newOwner), paper.state.notary))
        tx.addCommand(IndiaCommercialPaper.Commands.Move(), paper.state.data.owner)
    }

    /**
     * Intended to be called by the issuer of some commercial paper, when an owner has notified us that they wish
     * to redeem the paper. We must therefore send enough money to the key that owns the paper to satisfy the face
     * value, and then ensure the paper is removed from the ledger.
     *
     * @throws InsufficientBalanceException if the vault doesn't contain enough money to pay the redeemer.
     */
    @Throws(InsufficientBalanceException::class)
    fun generateRedeem(tx: TransactionBuilder, paper: StateAndRef<IndiaCommercialPaper.State>, vault: VaultService) {
        // Add the cash movement using the states in our vault.
        val amount = paper.state.data.faceValue.let { amount -> Amount(amount.quantity, amount.token.product) }
        vault.generateSpend(tx, amount, paper.state.data.owner)
        tx.addInputState(paper)
        tx.addCommand(IndiaCommercialPaper.Commands.Redeem(), paper.state.data.owner)
    }

    fun generateISIN(tx: TransactionBuilder, cp: StateAndRef<State>, isin: String): TransactionBuilder {
        tx.addInputState(cp)
        val newVersion = Integer(cp.state.data.version.toInt() + 1)
        tx.addOutputState(
                cp.state.data.copy(isin = isin, version = newVersion),
                cp.state.notary
        )
        tx.addCommand(Commands.ISINGeneration(isin), listOf(cp.state.data.owner))
        return tx;
    }

}

infix fun IndiaCommercialPaper.State.`owned by`(owner: CompositeKey) = copy(owner = owner)
infix fun IndiaCommercialPaper.State.`with notary`(notary: Party) = TransactionState(this, notary)
infix fun ICommercialPaperState.`owned by`(newOwner: CompositeKey) = withOwner(newOwner)

