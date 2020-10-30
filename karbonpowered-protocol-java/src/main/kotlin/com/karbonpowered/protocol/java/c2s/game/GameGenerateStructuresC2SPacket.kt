package com.karbonpowered.protocol.java.c2s.game

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket
import com.karbonpowered.protocol.java.data.Position

data class GameGenerateStructuresC2SPacket
@JvmOverloads
constructor(
    val position: Position = Position(),
    val levels: Int = 0,
    val keepJigsaws: Boolean = true
) : MinecraftPacket() {
    object Codec : com.karbonpowered.network.codec.Codec<GameGenerateStructuresC2SPacket> {
        override fun decode(buffer: MessageBuf): GameGenerateStructuresC2SPacket {
            val position = Position.Codec1_14.decode(buffer)
            val levels = buffer.readVarInt()
            val keepJigsaws = buffer.readBoolean()
            return GameGenerateStructuresC2SPacket(position, levels, keepJigsaws)
        }

        override fun encode(buffer: MessageBuf, message: GameGenerateStructuresC2SPacket): MessageBuf {
            Position.Codec1_14.encode(buffer, message.position)
            buffer.writeVarInt(message.levels)
            buffer.writeBoolean(message.keepJigsaws)
            return buffer
        }
    }
}