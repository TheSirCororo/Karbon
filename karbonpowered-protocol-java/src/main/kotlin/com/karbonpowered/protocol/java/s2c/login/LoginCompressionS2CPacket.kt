package com.karbonpowered.protocol.java.s2c.login

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket

data class LoginCompressionS2CPacket(
    val compressionThreshold: Int
) : MinecraftPacket() {
    object Codec : com.karbonpowered.network.codec.Codec<LoginCompressionS2CPacket> {
        override fun decode(buffer: MessageBuf): LoginCompressionS2CPacket {
            val compressionThreshold = buffer.readVarInt()
            return LoginCompressionS2CPacket(compressionThreshold)
        }

        override fun encode(buffer: MessageBuf, message: LoginCompressionS2CPacket): MessageBuf {
            buffer.writeVarInt(message.compressionThreshold)
            return buffer
        }
    }
}