package com.barclays.indiacp.cordapp.plugin

import com.barclays.indiacp.cordapp.api.IndiaCPApi
import com.barclays.indiacp.cordapp.contract.IndiaCommercialPaper
import com.barclays.indiacp.cordapp.protocol.investor.BuyerFlow
import com.barclays.indiacp.cordapp.protocol.issuer.DealEntryFlow
import com.barclays.indiacp.cordapp.protocol.issuer.ISINGenerationFlow
import com.barclays.indiacp.cordapp.protocol.issuer.IssueCPFlow
import com.esotericsoftware.kryo.Kryo
import net.corda.core.crypto.Party
import net.corda.core.node.CordaPluginRegistry
import java.util.function.Function

class IndiaCPPlugin : CordaPluginRegistry() {
    // A list of classes that expose web APIs.
    override val webApis = listOf(Function(::IndiaCPApi))
    // A list of protocol that are required for this cordapp
    override val requiredFlows: Map<String, Set<String>> = mapOf(
            DealEntryFlow::class.java.name to setOf(String::class.java.name, Party::class.java.name),
            IssueCPFlow::class.java.name to setOf(IndiaCPApi.CPJSONObject::class.java.name),
            ISINGenerationFlow::class.java.name to setOf(String::class.java.name, String::class.java.name)
    )
    override val servicePlugins = listOf(Function(BuyerFlow::Service))

    override fun registerRPCKryoTypes(kryo: Kryo): Boolean {
        kryo.apply {
            register(IndiaCPApi.CPJSONObject::class.java)
            register(IndiaCommercialPaper.State::class.java)
            register(IndiaCPApi.CPReferenceAndAcceptablePrice::class.java)
            register(IndiaCPApi.Cash::class.java)
            register(net.corda.contracts.CommercialPaper::class.java)
        }
        return true
    }

}