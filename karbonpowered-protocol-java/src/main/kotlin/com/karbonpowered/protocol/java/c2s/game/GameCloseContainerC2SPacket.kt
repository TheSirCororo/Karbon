package com.karbonpowered.protocol.java.c2s.game

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket

data class GameCloseContainerC2SPacket
@JvmOverloads
constructor(
    val syncId: Int = 0
) : MinecraftPacket() {
    object Codec : com.karbonpowered.network.codec.Codec<GameCloseContainerC2SPacket> {
        override fun decode(buffer: MessageBuf): GameCloseContainerC2SPacket {
            val containerId = buffer.readUnsignedByte().toInt()
            return GameCloseContainerC2SPacket(containerId)
        }

        override fun encode(buffer: MessageBuf, message: GameCloseContainerC2SPacket): MessageBuf {
            buffer.writeByte(message.syncId)
            return buffer
        }
    }
}