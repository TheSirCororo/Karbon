package com.karbonpowered.protocol.java.s2c.login

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket
import java.security.KeyFactory
import java.security.PublicKey
import java.security.spec.X509EncodedKeySpec

data class LoginEncryptionRequestS2CPacket(
    val serverId: String,
    val publicKey: PublicKey,
    val nonce: ByteArray
) : MinecraftPacket() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LoginEncryptionRequestS2CPacket

        if (serverId != other.serverId) return false
        if (publicKey != other.publicKey) return false
        if (!nonce.contentEquals(other.nonce)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = serverId.hashCode()
        result = 31 * result + publicKey.hashCode()
        result = 31 * result + nonce.contentHashCode()
        return result
    }

    object Codec : com.karbonpowered.network.codec.Codec<LoginEncryptionRequestS2CPacket> {
        val keyFactory = KeyFactory.getInstance("RSA")

        override fun decode(buffer: MessageBuf): LoginEncryptionRequestS2CPacket {
            val serverId = buffer.readString()
            val publicKey = keyFactory.generatePublic(X509EncodedKeySpec(buffer.readByteArray()))
            val nonce = buffer.readByteArray()
            return LoginEncryptionRequestS2CPacket(serverId, publicKey, nonce)
        }

        override fun encode(buffer: MessageBuf, message: LoginEncryptionRequestS2CPacket): MessageBuf {
            buffer.writeString(message.serverId)
            buffer.writeByteArray(message.publicKey.encoded)
            buffer.writeByteArray(message.nonce)
            return buffer
        }
    }
}