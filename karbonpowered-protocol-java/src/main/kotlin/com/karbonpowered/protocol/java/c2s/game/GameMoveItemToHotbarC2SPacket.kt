package com.karbonpowered.protocol.java.c2s.game

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket

data class GameMoveItemToHotbarC2SPacket
@JvmOverloads
constructor(
    val slot: Int = 0
) : MinecraftPacket() {
    object Codec : com.karbonpowered.network.codec.Codec<GameMoveItemToHotbarC2SPacket> {
        override fun decode(buffer: MessageBuf): GameMoveItemToHotbarC2SPacket {
            val slot = buffer.readVarInt()
            return GameMoveItemToHotbarC2SPacket(slot)
        }

        override fun encode(buffer: MessageBuf, message: GameMoveItemToHotbarC2SPacket): MessageBuf {
            buffer.writeVarInt(message.slot)
            return buffer
        }
    }
}