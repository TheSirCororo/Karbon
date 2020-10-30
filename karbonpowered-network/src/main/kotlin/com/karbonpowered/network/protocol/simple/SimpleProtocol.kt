package com.karbonpowered.network.protocol.simple

import com.karbonpowered.network.codec.Codec
import com.karbonpowered.network.message.Message
import com.karbonpowered.network.message.MessageBinding
import com.karbonpowered.network.message.MessageHandler
import com.karbonpowered.network.protocol.AbstractProtocol
import com.karbonpowered.network.service.CodecLookupService
import com.karbonpowered.network.service.HandlerLookupService
import kotlin.reflect.KClass

abstract class SimpleProtocol(
    name: String,
    maxPackets: Int = 0
) : AbstractProtocol(name) {
    val codecLookup = CodecLookupService(maxPackets)
    val handlerLookup = HandlerLookupService()

    override val bindings: Collection<MessageBinding<*>>
        get() = codecLookup.bindings

    override fun <M : Message> getMessageHandlers(message: Class<M>): Set<MessageHandler<M, *>> =
        handlerLookup.find(message)

    override fun <M : Message> getBinding(message: Class<M>): MessageBinding<M> =
        codecLookup.find(message)!!

    override fun getBinding(opcode: Int): MessageBinding<*> =
        codecLookup.find(opcode)

    fun <M : Message, C : Codec<M>, H : MessageHandler<*, M>> registerMessage(
        message: Class<M>,
        codec: Class<C>,
        opcode: Int? = null
    ): MessageBinding<M> = codecLookup.bind(message, codec, opcode)


    fun <M : Message, C : Codec<M>, H : MessageHandler<*, M>> registerMessage(
        message: KClass<M>,
        codec: KClass<C>,
        opcode: Int? = null
    ): MessageBinding<M> = registerMessage(message.java, codec.java, opcode)
}