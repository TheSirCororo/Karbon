package com.karbonpowered.protocol.java.c2s.game

import com.karbonpowered.network.codec.Codec
import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket

data class GamePlayerPositionC2SPacket
@JvmOverloads
constructor(
    val x: Double = 0.0,
    val y: Double = 0.0,
    val z: Double = 0.0,
    val onGround: Boolean = true
) : MinecraftPacket() {
    object Codec1_8 : Codec<GamePlayerPositionC2SPacket> {
        override fun decode(buffer: MessageBuf): GamePlayerPositionC2SPacket {
            val x = buffer.readDouble()
            val y = buffer.readDouble()
            val z = buffer.readDouble()
            val onGround = buffer.readBoolean()
            return GamePlayerPositionC2SPacket(x, y, z, onGround)
        }

        override fun encode(buffer: MessageBuf, message: GamePlayerPositionC2SPacket): MessageBuf {
            buffer.writeDouble(message.x)
            buffer.writeDouble(message.x)
            buffer.writeDouble(message.z)
            buffer.writeBoolean(message.onGround)
            return buffer
        }
    }

    object Codec1_7 : Codec<GamePlayerPositionC2SPacket> {
        override fun decode(buffer: MessageBuf): GamePlayerPositionC2SPacket {
            val x = buffer.readDouble()
            val y = buffer.readDouble() // Feet position
            buffer.readDouble() // Head position (not used)
            val z = buffer.readDouble()
            val onGround = buffer.readBoolean()
            return GamePlayerPositionC2SPacket(x, y, z, onGround)
        }

        override fun encode(buffer: MessageBuf, message: GamePlayerPositionC2SPacket): MessageBuf {
            buffer.writeDouble(message.x)
            buffer.writeDouble(message.y)
            buffer.writeDouble(message.y + 1.62) // Head position (not used)
            buffer.writeDouble(message.z)
            buffer.writeBoolean(message.onGround)
            return buffer
        }
    }
}