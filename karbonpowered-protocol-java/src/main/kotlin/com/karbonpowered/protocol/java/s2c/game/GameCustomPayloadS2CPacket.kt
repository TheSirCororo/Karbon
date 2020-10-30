package com.karbonpowered.protocol.java.s2c.game

import com.karbonpowered.catalog.NamespacedKey
import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket

data class GameCustomPayloadS2CPacket(
    val channel: NamespacedKey,
    val data: ByteArray
) : MinecraftPacket() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GameCustomPayloadS2CPacket

        if (channel != other.channel) return false
        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = channel.hashCode()
        result = 31 * result + data.contentHashCode()
        return result
    }

    object Codec : com.karbonpowered.network.codec.Codec<GameCustomPayloadS2CPacket> {
        override fun decode(buffer: MessageBuf): GameCustomPayloadS2CPacket {
            val rawChannel = buffer.readString()
            val channel = NamespacedKey.resolve(rawChannel)
            val data = buffer.readBytes(buffer.available())
            return GameCustomPayloadS2CPacket(channel, data)
        }

        override fun encode(buffer: MessageBuf, message: GameCustomPayloadS2CPacket): MessageBuf {
            buffer.writeString(message.channel.formatted())
            buffer.writeBytes(message.data)
            return buffer
        }
    }
}