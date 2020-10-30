package com.karbonpowered.protocol.java.c2s.game

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket

data class GameEntityNbtRequestPacketC2SPacket
@JvmOverloads
constructor(
    val transactionId: Int = 0,
    val entityId: Int = 0
) : MinecraftPacket() {
    object Codec : com.karbonpowered.network.codec.Codec<GameEntityNbtRequestPacketC2SPacket> {
        override fun decode(buffer: MessageBuf): GameEntityNbtRequestPacketC2SPacket {
            val transactionId = buffer.readVarInt()
            val entityId = buffer.readVarInt()
            return GameEntityNbtRequestPacketC2SPacket(transactionId, entityId)
        }

        override fun encode(buffer: MessageBuf, message: GameEntityNbtRequestPacketC2SPacket): MessageBuf {
            buffer.writeVarInt(message.transactionId)
            buffer.writeVarInt(message.entityId)
            return buffer
        }
    }
}