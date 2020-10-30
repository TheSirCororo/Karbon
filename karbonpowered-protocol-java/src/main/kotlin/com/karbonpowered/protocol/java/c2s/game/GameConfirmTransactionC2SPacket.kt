package com.karbonpowered.protocol.java.c2s.game

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket

data class GameConfirmTransactionC2SPacket
@JvmOverloads
constructor(
    val syncId: Int = 0,
    val actionId: Int = 0,
    val isAccepted: Boolean = true
) : MinecraftPacket() {
    object Codec : com.karbonpowered.network.codec.Codec<GameConfirmTransactionC2SPacket> {
        override fun decode(buffer: MessageBuf): GameConfirmTransactionC2SPacket {
            val containerId = buffer.readUnsignedByte().toInt()
            val actionId = buffer.readShort().toInt()
            val isAccepted = buffer.readBoolean()
            return GameConfirmTransactionC2SPacket(containerId, actionId, isAccepted)
        }

        override fun encode(buffer: MessageBuf, message: GameConfirmTransactionC2SPacket): MessageBuf {
            buffer.writeByte(message.syncId)
            buffer.writeShort(message.actionId)
            buffer.writeBoolean(message.isAccepted)
            return buffer
        }
    }
}