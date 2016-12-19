package com.barclays.indiacp.cordapp.protocol.issuer

import co.paralleluniverse.fibers.Suspendable
import com.barclays.indiacp.cordapp.contract.IndiaCommercialPaper
import com.barclays.indiacp.cordapp.utilities.CPUtils
import net.corda.core.TransientProperty
import net.corda.core.contracts.StateAndRef
import net.corda.core.contracts.TransactionState
import net.corda.core.contracts.TransactionType
import net.corda.core.flows.FlowLogic
import net.corda.core.node.NodeInfo
import net.corda.core.seconds
import net.corda.core.transactions.SignedTransaction
import net.corda.core.utilities.ProgressTracker
import net.corda.flows.NotaryFlow
import java.security.KeyPair
import java.time.Instant

/**
 * This whole class is really part of a demo just to initiate the agreement of a deal with a simple
 * API call from a single party without bi-directional access to the database of offers etc.
 *
 * In the "real world", we'd probably have the offers sitting in the platform prior to the agreement step
 * or the protocol would have to reach out to external systems (or users) to verify the deals.
 */
class ISINGenerationFlow(val cpRefId: String, val isin: String) : FlowLogic<SignedTransaction>() {

    companion object {
        //val PROSPECTUS_HASH = SecureHash.parse("decd098666b9657314870e192ced0c3519c2c9d395507a238338f8d003929de9")

        object ISIN_GENERATION : ProgressTracker.Step("Issuing and timestamping ISIN for the issued commercial paper")
        object OBTAINING_NOTARY_SIGNATURE : ProgressTracker.Step("Obtaining Notary Signature")
        object NOTARY_SIGNATURE_OBTAINED : ProgressTracker.Step("Notary Signature Obtained")
        object RECORDING_ISIN : ProgressTracker.Step("Recording Transaction in Local Storage")
        object ISIN_RECORDED : ProgressTracker.Step("Transaction Recorded in Local Storage")

        // We vend a progress tracker that already knows there's going to be a TwoPartyTradingProtocol involved at some
        // point: by setting up the tracker in advance, the user can see what's coming in more detail, instead of being
        // surprised when it appears as a new set of tasks below the current one.
        fun tracker() = ProgressTracker(ISIN_GENERATION, OBTAINING_NOTARY_SIGNATURE, NOTARY_SIGNATURE_OBTAINED, RECORDING_ISIN, ISIN_RECORDED)
    }

    override val progressTracker = tracker()

    @Suppress("UNCHECKED_CAST")
    internal val cpIssueToStampWithISIN: StateAndRef<IndiaCommercialPaper.State> by TransientProperty {
        val indiaCP = CPUtils.getReferencedCommercialPaper(serviceHub, cpRefId)

        val state = serviceHub.loadState(indiaCP.ref) as TransactionState<IndiaCommercialPaper.State>
        StateAndRef(state, indiaCP.ref)
    }

    val myKeyPair: KeyPair get() {
        val myName = serviceHub.myInfo.legalIdentity.name
        val owner = cpIssueToStampWithISIN.state.data.owner
        return serviceHub.keyManagementService.toKeyPair(owner.keys)
    }

    val notaryNode: NodeInfo get() =
    serviceHub.networkMapCache.notaryNodes.filter { it.notaryIdentity == cpIssueToStampWithISIN.state.notary }.single()

    @Suspendable
    override fun call(): SignedTransaction {
        progressTracker.currentStep = ISIN_GENERATION

        val cpOwnerKey = serviceHub.keyManagementService.toKeyPair(cpIssueToStampWithISIN.state.data.owner.keys)

        val ptx = TransactionType.General.Builder(notaryNode.notaryIdentity)

        val tx = IndiaCommercialPaper().generateISIN(ptx, cpIssueToStampWithISIN, isin)

        // Attach the prospectus.
        //tx.addAttachment(serviceHub.storageService.attachments.openAttachment(PROSPECTUS_HASH)!!.id)

        // Requesting timestamping, all CP must be timestamped.
        tx.setTime(Instant.now(), 30.seconds)

        // Sign it as ourselves.
        tx.signWith(cpOwnerKey)

        // Get the notary to sign the timestamp
        progressTracker.currentStep = OBTAINING_NOTARY_SIGNATURE
        val notarySig = subFlow(NotaryFlow.Client(tx.toSignedTransaction(false)))
        progressTracker.currentStep = NOTARY_SIGNATURE_OBTAINED
        tx.addSignatureUnchecked(notarySig)

        // Commit it to local storage.
        val stx = tx.toSignedTransaction(true)
        progressTracker.currentStep = RECORDING_ISIN
        serviceHub.recordTransactions(listOf(stx))
        progressTracker.currentStep = ISIN_RECORDED

        return stx
    }
}
