package com.karbonpowered.protocol.java.s2c.login

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket
import com.karbonpowered.text.Text
import com.karbonpowered.text.serializer.TextSerializers

data class LoginDisconnectS2CPacket(
    val reason: Text
) : MinecraftPacket() {
    object Codec : com.karbonpowered.network.codec.Codec<LoginDisconnectS2CPacket> {
        override fun decode(buffer: MessageBuf): LoginDisconnectS2CPacket {
            val rawText = buffer.readString()
            val reason = TextSerializers.JSON.deserialize(rawText)
            return LoginDisconnectS2CPacket(reason)
        }

        override fun encode(buffer: MessageBuf, message: LoginDisconnectS2CPacket): MessageBuf {
            val rawText = TextSerializers.JSON.serialize(message.reason)
            buffer.writeString(rawText)
            return buffer
        }
    }
}