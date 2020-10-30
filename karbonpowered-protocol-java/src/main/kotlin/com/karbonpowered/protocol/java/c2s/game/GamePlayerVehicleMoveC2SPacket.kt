package com.karbonpowered.protocol.java.c2s.game

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket

data class GamePlayerVehicleMoveC2SPacket
@JvmOverloads
constructor(
    val x: Double = 0.0,
    val y: Double = 0.0,
    val z: Double = 0.0,
    val yaw: Float = 0.0f,
    val pitch: Float = 0.0f
) : MinecraftPacket() {
    object Codec : com.karbonpowered.network.codec.Codec<GamePlayerVehicleMoveC2SPacket> {
        override fun decode(buffer: MessageBuf): GamePlayerVehicleMoveC2SPacket {
            val x = buffer.readDouble()
            val y = buffer.readDouble()
            val z = buffer.readDouble()
            val yaw = buffer.readFloat()
            val pitch = buffer.readFloat()
            return GamePlayerVehicleMoveC2SPacket(x, y, z, yaw, pitch)
        }

        override fun encode(buffer: MessageBuf, message: GamePlayerVehicleMoveC2SPacket): MessageBuf {
            buffer.writeDouble(message.x)
            buffer.writeDouble(message.x)
            buffer.writeDouble(message.z)
            buffer.writeFloat(message.yaw)
            buffer.writeFloat(message.pitch)
            return buffer
        }
    }
}