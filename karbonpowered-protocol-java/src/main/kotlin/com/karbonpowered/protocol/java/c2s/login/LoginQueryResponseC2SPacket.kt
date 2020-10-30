package com.karbonpowered.protocol.java.c2s.login

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket

data class LoginQueryResponseC2SPacket(
    val queryId: Int,
    val response: ByteArray?
) : MinecraftPacket() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LoginQueryResponseC2SPacket

        if (queryId != other.queryId) return false
        if (response != null) {
            if (other.response == null) return false
            if (!response.contentEquals(other.response)) return false
        } else if (other.response != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = queryId
        result = 31 * result + (response?.contentHashCode() ?: 0)
        return result
    }

    object Codec : com.karbonpowered.network.codec.Codec<LoginQueryResponseC2SPacket> {
        override fun decode(buffer: MessageBuf): LoginQueryResponseC2SPacket {
            val queryId = buffer.readVarInt()
            val response = if (buffer.readBoolean()) {
                buffer.readBytes(buffer.available())
            } else null
            return LoginQueryResponseC2SPacket(queryId, response)
        }

        override fun encode(buffer: MessageBuf, message: LoginQueryResponseC2SPacket): MessageBuf {
            buffer.writeVarInt(message.queryId)
            buffer.writeBoolean(message.response != null)
            message.response?.let { buffer.writeBytes(it) }
            return buffer
        }
    }
}