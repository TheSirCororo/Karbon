package com.karbonpowered.protocol.java.c2s.game

import com.karbonpowered.catalog.NamespacedKey
import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket

data class GameCustomPayloadC2SPacket
@JvmOverloads
constructor(
    val channel: NamespacedKey,
    val data: ByteArray = ByteArray(0)
) : MinecraftPacket() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GameCustomPayloadC2SPacket

        if (channel != other.channel) return false
        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = channel.hashCode()
        result = 31 * result + data.contentHashCode()
        return result
    }

    object Codec : com.karbonpowered.network.codec.Codec<GameCustomPayloadC2SPacket> {
        override fun decode(buffer: MessageBuf): GameCustomPayloadC2SPacket {
            val rawChannel = buffer.readString()
            val channel = NamespacedKey.resolve(rawChannel)
            val data = buffer.readBytes(buffer.available())
            return GameCustomPayloadC2SPacket(channel, data)
        }

        override fun encode(buffer: MessageBuf, message: GameCustomPayloadC2SPacket): MessageBuf {
            buffer.writeString(message.channel.formatted())
            buffer.writeBytes(message.data)
            return buffer
        }
    }
}