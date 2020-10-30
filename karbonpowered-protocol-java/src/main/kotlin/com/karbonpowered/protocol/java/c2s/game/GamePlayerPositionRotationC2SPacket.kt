package com.karbonpowered.protocol.java.c2s.game

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket

data class GamePlayerPositionRotationC2SPacket
@JvmOverloads
constructor(
    val x: Double = 0.0,
    val y: Double = 0.0,
    val z: Double = 0.0,
    val yaw: Float = 0.0f,
    val pitch: Float = 0.0f,
    val onGround: Boolean = true
) : MinecraftPacket() {
    object Codec : com.karbonpowered.network.codec.Codec<GamePlayerPositionRotationC2SPacket> {
        override fun decode(buffer: MessageBuf): GamePlayerPositionRotationC2SPacket {
            val x = buffer.readDouble()
            val y = buffer.readDouble()
            val z = buffer.readDouble()
            val yaw = buffer.readFloat()
            val pitch = buffer.readFloat()
            val onGround = buffer.readBoolean()
            return GamePlayerPositionRotationC2SPacket(x, y, z, yaw, pitch, onGround)
        }

        override fun encode(buffer: MessageBuf, message: GamePlayerPositionRotationC2SPacket): MessageBuf {
            buffer.writeDouble(message.x)
            buffer.writeDouble(message.x)
            buffer.writeDouble(message.z)
            buffer.writeFloat(message.yaw)
            buffer.writeFloat(message.pitch)
            buffer.writeBoolean(message.onGround)
            return buffer
        }
    }
}