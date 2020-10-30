package com.karbonpowered.network.protocol.keyed

import com.karbonpowered.network.codec.Codec
import com.karbonpowered.network.message.Message
import com.karbonpowered.network.message.MessageBinding
import com.karbonpowered.network.protocol.AbstractProtocol
import com.karbonpowered.network.service.CodecLookupService
import com.karbonpowered.network.service.HandlerLookupService
import java.util.concurrent.ConcurrentHashMap

abstract class KeyedProtocol(
    name: String,
    val maxPackets: Int
) : AbstractProtocol(name) {
    val codecLookup = ConcurrentHashMap<String, CodecLookupService>()
    private val handlerLookup = ConcurrentHashMap<String, HandlerLookupService>()

    fun getHandlerLookupService(key: String): HandlerLookupService? = handlerLookup[key]

    fun getCodecLookupService(key: String): CodecLookupService? = codecLookup[key]

    fun <M : Message, C : Codec<M>> registerMessage(
        key: String,
        message: Class<M>,
        codec: Class<C>,
        opcode: Int? = null
    ): MessageBinding<M> {
        val codecLookup = codecLookup.getOrPut(key) {
            CodecLookupService(maxPackets)
        }
        return codecLookup.bind(message, codec, opcode)
    }
}