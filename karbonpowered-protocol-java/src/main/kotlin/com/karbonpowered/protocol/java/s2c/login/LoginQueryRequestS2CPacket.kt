package com.karbonpowered.protocol.java.s2c.login

import com.karbonpowered.catalog.NamespacedKey
import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket
import java.io.IOException

data class LoginQueryRequestS2CPacket(
    val queryId: Int,
    val channel: NamespacedKey,
    val payload: ByteArray
) : MinecraftPacket() {
    object Codec : com.karbonpowered.network.codec.Codec<LoginQueryRequestS2CPacket> {
        override fun decode(buffer: MessageBuf): LoginQueryRequestS2CPacket {
            val queryId = buffer.readVarInt()
            val channel = NamespacedKey.resolve(buffer.readString())
            val payload = run {
                val size = buffer.available()
                if (size < 0 || size > 1048576) {
                    throw IOException("Payload may not be larger than 1048576 bytes")
                }
                buffer.readBytes(size)
            }
            return LoginQueryRequestS2CPacket(queryId, channel, payload)
        }

        override fun encode(buffer: MessageBuf, message: LoginQueryRequestS2CPacket): MessageBuf {
            buffer.writeVarInt(message.queryId)
            buffer.writeString(message.channel.formatted())
            buffer.writeBytes(message.payload)
            return buffer
        }
    }
}