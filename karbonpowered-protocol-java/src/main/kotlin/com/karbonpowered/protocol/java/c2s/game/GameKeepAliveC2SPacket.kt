package com.karbonpowered.protocol.java.c2s.game

import com.karbonpowered.network.codec.Codec
import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket

data class GameKeepAliveC2SPacket
@JvmOverloads
constructor(
    val id: Long = 0
) : MinecraftPacket() {
    object Codec_1_12_2 : Codec<GameKeepAliveC2SPacket> {
        override fun decode(buffer: MessageBuf): GameKeepAliveC2SPacket {
            val id = buffer.readLong()
            return GameKeepAliveC2SPacket(id)
        }

        override fun encode(buffer: MessageBuf, message: GameKeepAliveC2SPacket): MessageBuf {
            buffer.writeLong(message.id)
            return buffer
        }
    }

    object Codec_1_8 : Codec<GameKeepAliveC2SPacket> {
        override fun decode(buffer: MessageBuf): GameKeepAliveC2SPacket {
            val id = buffer.readVarInt().toLong()
            return GameKeepAliveC2SPacket(id)
        }

        override fun encode(buffer: MessageBuf, message: GameKeepAliveC2SPacket): MessageBuf {
            buffer.writeVarInt(message.id.toInt())
            return buffer
        }
    }

    object Codec_1_7 : Codec<GameKeepAliveC2SPacket> {
        override fun decode(buffer: MessageBuf): GameKeepAliveC2SPacket {
            val id = buffer.readInt().toLong()
            return GameKeepAliveC2SPacket(id)
        }

        override fun encode(buffer: MessageBuf, message: GameKeepAliveC2SPacket): MessageBuf {
            buffer.writeInt(message.id.toInt())
            return buffer
        }
    }
}