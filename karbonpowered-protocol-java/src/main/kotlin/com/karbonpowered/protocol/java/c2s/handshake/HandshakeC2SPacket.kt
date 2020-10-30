package com.karbonpowered.protocol.java.c2s.handshake

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket
import com.karbonpowered.protocol.java.ProtocolState

data class HandshakeC2SPacket(
    val protocolVersion: Int,
    val address: String,
    val port: Int,
    val intendedState: ProtocolState,
) : MinecraftPacket() {
    object Codec : com.karbonpowered.network.codec.Codec<HandshakeC2SPacket> {
        override fun decode(buffer: MessageBuf): HandshakeC2SPacket {
            val protocolVersion = buffer.readVarInt()
            val address = buffer.readString()
            val port = buffer.readShort().toInt()
            val intendedState = ProtocolState.values()[buffer.readVarInt()]
            return HandshakeC2SPacket(protocolVersion, address, port, intendedState)
        }

        override fun encode(buffer: MessageBuf, message: HandshakeC2SPacket): MessageBuf {
            buffer.writeVarInt(message.protocolVersion)
            buffer.writeString(message.address)
            buffer.writeVarInt(message.port)
            buffer.writeVarInt(message.intendedState.ordinal)
            return buffer
        }
    }
}