package com.karbonpowered.protocol.java

import com.karbonpowered.network.codec.Codec
import com.karbonpowered.network.message.Message
import com.karbonpowered.network.message.MessageBinding
import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.network.message.MessageHandler
import com.karbonpowered.network.protocol.AbstractProtocol
import com.karbonpowered.network.service.CodecLookupService
import com.karbonpowered.network.service.HandlerLookupService
import kotlin.reflect.KClass

open class MinecraftProtocol(name: String, val isServerSide: Boolean, maxPackets: Int = 0) : AbstractProtocol(name) {
    val server2clientCodecLookup = CodecLookupService(maxPackets)
    val client2serverCodecLookup = CodecLookupService(maxPackets)

    val handlerLookup = HandlerLookupService()

    override fun <M : Message> getMessageHandlers(message: Class<M>): Set<MessageHandler<M, *>> =
        handlerLookup.find(message)

    override fun writeHeader(header: MessageBuf, binding: MessageBinding<*>, data: MessageBuf): MessageBuf {
        header.writeVarInt(binding.opcode)
        return header
    }

    override fun readHeader(buf: MessageBuf): MessageBinding<*> {
        val opcode = buf.readVarInt()
        return if (isServerSide) {
            client2serverCodecLookup.find(opcode)
        } else {
            server2clientCodecLookup.find(opcode)
        }
    }

    override val bindings: Collection<MessageBinding<*>>
        get() = server2clientCodecLookup.bindings + client2serverCodecLookup.bindings

    override fun <M : Message> getBinding(message: Class<M>): MessageBinding<M> =
        (client2serverCodecLookup.find(message) ?: server2clientCodecLookup.find(message))!!

    override fun getBinding(opcode: Int): MessageBinding<*> {
        TODO("Not yet implemented")
    }

    fun <M : MinecraftPacket, C : Codec<M>> registerPacket(
        isServerBound: Boolean,
        packet: Class<M>,
        codec: Class<C>,
        opcode: Int? = null
    ) {
        if (isServerBound) {
            client2serverCodecLookup.bind(packet, codec, opcode)
        } else {
            server2clientCodecLookup.bind(packet, codec, opcode)
        }
    }

    fun <M : MinecraftPacket, C : Codec<M>> registerPacket(
        isServerBound: Boolean,
        packet: KClass<M>,
        codec: KClass<C>,
        opcode: Int? = null
    ) = registerPacket(isServerBound, packet.java, codec.java, opcode)

    inline fun <reified M : MinecraftPacket, reified C : Codec<M>> registerPacket(
        isServerBound: Boolean,
        opcode: Int? = null
    ) =
        registerPacket(isServerBound, M::class, C::class, opcode)

    fun <M : MinecraftPacket> registerHandler(packet: Class<M>, handler: MessageHandler<M, *>) =
        handlerLookup.addHandler(packet, handler)

    fun <M : MinecraftPacket> registerHandler(packet: KClass<M>, handler: MessageHandler<M, *>) =
        handlerLookup.addHandler(packet.java, handler)

    inline fun <reified M : MinecraftPacket> registerHandler(handler: MessageHandler<M, *>) =
        handlerLookup.addHandler(M::class.java, handler)
}