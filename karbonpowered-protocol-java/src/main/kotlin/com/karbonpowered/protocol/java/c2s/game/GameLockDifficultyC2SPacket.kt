package com.karbonpowered.protocol.java.c2s.game

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket

data class GameLockDifficultyC2SPacket
@JvmOverloads
constructor(
    val isLocked: Boolean = true
) : MinecraftPacket() {
    object Codec : com.karbonpowered.network.codec.Codec<GameLockDifficultyC2SPacket> {
        override fun decode(buffer: MessageBuf): GameLockDifficultyC2SPacket {
            val isLocked = buffer.readBoolean()
            return GameLockDifficultyC2SPacket(isLocked)
        }

        override fun encode(buffer: MessageBuf, message: GameLockDifficultyC2SPacket): MessageBuf {
            buffer.writeBoolean(message.isLocked)
            return buffer
        }
    }
}