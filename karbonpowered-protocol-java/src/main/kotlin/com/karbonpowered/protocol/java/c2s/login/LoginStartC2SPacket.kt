package com.karbonpowered.protocol.java.c2s.login

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket

data class LoginStartC2SPacket(
    val username: String
) : MinecraftPacket() {
    object Codec : com.karbonpowered.network.codec.Codec<LoginStartC2SPacket> {
        override fun decode(buffer: MessageBuf): LoginStartC2SPacket {
            val username = buffer.readString()
            return LoginStartC2SPacket(username)
        }

        override fun encode(buffer: MessageBuf, message: LoginStartC2SPacket): MessageBuf {
            buffer.writeString(message.username)
            return buffer
        }
    }
}