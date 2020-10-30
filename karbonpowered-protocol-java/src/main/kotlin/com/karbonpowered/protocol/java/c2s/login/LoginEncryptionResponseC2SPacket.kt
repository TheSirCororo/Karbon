package com.karbonpowered.protocol.java.c2s.login

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket
import java.security.PublicKey
import javax.crypto.Cipher
import javax.crypto.SecretKey

data class LoginEncryptionResponseC2SPacket(
    val encryptedSecretKey: ByteArray,
    val encryptedNonce: ByteArray
) : MinecraftPacket() {
    constructor(secretKey: SecretKey, publicKey: PublicKey, nonce: ByteArray) : this(
        Cipher.getInstance(publicKey.algorithm).apply {
            init(1, publicKey)
        }.doFinal(secretKey.encoded),
        Cipher.getInstance(publicKey.algorithm).apply {
            init(1, publicKey)
        }.doFinal(nonce)
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LoginEncryptionResponseC2SPacket

        if (!encryptedSecretKey.contentEquals(other.encryptedSecretKey)) return false
        if (!encryptedNonce.contentEquals(other.encryptedNonce)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = encryptedSecretKey.contentHashCode()
        result = 31 * result + encryptedNonce.contentHashCode()
        return result
    }

    object Codec : com.karbonpowered.network.codec.Codec<LoginEncryptionResponseC2SPacket> {
        override fun decode(buffer: MessageBuf): LoginEncryptionResponseC2SPacket {
            val encryptedSecretKey = buffer.readByteArray()
            val encryptedNonce = buffer.readByteArray()
            return LoginEncryptionResponseC2SPacket(encryptedSecretKey, encryptedNonce)
        }

        override fun encode(buffer: MessageBuf, message: LoginEncryptionResponseC2SPacket): MessageBuf {
            buffer.writeByteArray(message.encryptedSecretKey)
            buffer.writeByteArray(message.encryptedNonce)
            return buffer
        }
    }
}