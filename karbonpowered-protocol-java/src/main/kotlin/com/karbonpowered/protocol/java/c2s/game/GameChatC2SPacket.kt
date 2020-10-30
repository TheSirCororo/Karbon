package com.karbonpowered.protocol.java.c2s.game

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket

data class GameChatC2SPacket
@JvmOverloads
constructor(
    val message: String = ""
) : MinecraftPacket() {
    object Codec : com.karbonpowered.network.codec.Codec<GameChatC2SPacket> {
        override fun decode(buffer: MessageBuf): GameChatC2SPacket {
            val message = buffer.readString()
            return GameChatC2SPacket(message)
        }

        override fun encode(buffer: MessageBuf, message: GameChatC2SPacket): MessageBuf {
            buffer.writeString(message.message)
            return buffer
        }
    }
}