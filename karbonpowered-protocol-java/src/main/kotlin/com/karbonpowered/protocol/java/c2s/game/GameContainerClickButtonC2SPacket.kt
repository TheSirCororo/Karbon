package com.karbonpowered.protocol.java.c2s.game

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket

data class GameContainerClickButtonC2SPacket
@JvmOverloads
constructor(
    val syncId: Int = 0,
    val buttonId: Int = 0
) : MinecraftPacket() {
    object Codec : com.karbonpowered.network.codec.Codec<GameContainerClickButtonC2SPacket> {
        override fun decode(buffer: MessageBuf): GameContainerClickButtonC2SPacket {
            val containerId = buffer.readUnsignedByte().toInt()
            val buttonId = buffer.readByte().toInt()
            return GameContainerClickButtonC2SPacket(containerId, buttonId)
        }

        override fun encode(buffer: MessageBuf, message: GameContainerClickButtonC2SPacket): MessageBuf {
            buffer.writeByte(message.syncId)
            buffer.writeByte(message.buttonId)
            return buffer
        }
    }
}