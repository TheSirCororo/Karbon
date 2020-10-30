package com.karbonpowered.protocol.java.c2s.game

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket

data class GamePlayerRotationC2SPacket
@JvmOverloads
constructor(
    val yaw: Float = 0.0f,
    val pitch: Float = 0.0f,
    val onGround: Boolean = true
) : MinecraftPacket() {
    object Codec : com.karbonpowered.network.codec.Codec<GamePlayerRotationC2SPacket> {
        override fun decode(buffer: MessageBuf): GamePlayerRotationC2SPacket {
            val yaw = buffer.readFloat()
            val pitch = buffer.readFloat()
            val onGround = buffer.readBoolean()
            return GamePlayerRotationC2SPacket(yaw, pitch, onGround)
        }

        override fun encode(buffer: MessageBuf, message: GamePlayerRotationC2SPacket): MessageBuf {
            buffer.writeFloat(message.yaw)
            buffer.writeFloat(message.pitch)
            buffer.writeBoolean(message.onGround)
            return buffer
        }
    }
}