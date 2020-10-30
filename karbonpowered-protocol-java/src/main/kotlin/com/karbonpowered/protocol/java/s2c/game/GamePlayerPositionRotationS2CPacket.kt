package com.karbonpowered.protocol.java.s2c.game

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket

data class GamePlayerPositionRotationS2CPacket(
    val x: Double,
    val y: Double,
    val z: Double,
    val yaw: Float,
    val pitch: Float,
    val teleportId: Int,
    val relative: List<PositionElement> = emptyList()
) : MinecraftPacket() {
    enum class PositionElement(val bit: Int) {
        X(0x01),
        Y(0x02),
        Z(0x04),
        Y_ROT(0x08),
        X_ROT(0x10)
    }

    object Codec : com.karbonpowered.network.codec.Codec<GamePlayerPositionRotationS2CPacket> {
        override fun decode(buffer: MessageBuf): GamePlayerPositionRotationS2CPacket {
            val x = buffer.readDouble()
            val y = buffer.readDouble()
            val z = buffer.readDouble()
            val yaw = buffer.readFloat()
            val pitch = buffer.readFloat()
            val flags = buffer.readByte().toInt()
            val relative = ArrayList<PositionElement>()
            PositionElement.values().forEach { element ->
                if ((flags and element.bit) == element.bit) relative.add(element)
            }
            val teleportId = buffer.readVarInt()
            return GamePlayerPositionRotationS2CPacket(
                x, y, z, yaw, pitch, teleportId, relative
            )
        }

        override fun encode(buffer: MessageBuf, message: GamePlayerPositionRotationS2CPacket): MessageBuf {
            buffer.writeDouble(message.x)
            buffer.writeDouble(message.y)
            buffer.writeDouble(message.z)
            buffer.writeFloat(message.yaw)
            buffer.writeFloat(message.pitch)
            var flags = 0
            message.relative.forEach { element ->
                flags = flags or element.bit
            }
            buffer.writeByte(flags)
            buffer.writeVarInt(message.teleportId)
            return buffer
        }
    }
}