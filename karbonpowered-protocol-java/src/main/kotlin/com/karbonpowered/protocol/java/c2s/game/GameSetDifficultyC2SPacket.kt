package com.karbonpowered.protocol.java.c2s.game

import com.karbonpowered.api.world.difficulty.Difficulties
import com.karbonpowered.api.world.difficulty.Difficulty
import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MagicValues
import com.karbonpowered.protocol.java.MinecraftPacket

data class GameSetDifficultyC2SPacket
@JvmOverloads
constructor(
    val difficulty: Difficulty = Difficulties.NORMAL
) : MinecraftPacket() {
    object Codec : com.karbonpowered.network.codec.Codec<GameSetDifficultyC2SPacket> {
        override fun decode(buffer: MessageBuf): GameSetDifficultyC2SPacket {
            val difficulty = MagicValues.key(Difficulty::class, buffer.readByte())
            return GameSetDifficultyC2SPacket(difficulty)
        }

        override fun encode(buffer: MessageBuf, message: GameSetDifficultyC2SPacket): MessageBuf {
            buffer.writeByte(MagicValues.value(message.difficulty))
            return buffer
        }
    }
}