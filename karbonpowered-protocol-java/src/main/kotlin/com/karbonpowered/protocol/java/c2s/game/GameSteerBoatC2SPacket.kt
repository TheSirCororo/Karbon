package com.karbonpowered.protocol.java.c2s.game

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket

data class GameSteerBoatC2SPacket
@JvmOverloads
constructor(
    val rightPaddleTurning: Boolean = true,
    val leftPaddleTurning: Boolean = true
) : MinecraftPacket() {
    object Codec : com.karbonpowered.network.codec.Codec<GameSteerBoatC2SPacket> {
        override fun decode(buffer: MessageBuf): GameSteerBoatC2SPacket {
            val rightPaddleTurning = buffer.readBoolean()
            val leftPaddleTurning = buffer.readBoolean()
            return GameSteerBoatC2SPacket(rightPaddleTurning, leftPaddleTurning)
        }

        override fun encode(buffer: MessageBuf, message: GameSteerBoatC2SPacket): MessageBuf {
            buffer.writeBoolean(message.rightPaddleTurning)
            buffer.writeBoolean(message.leftPaddleTurning)
            return buffer
        }
    }
}