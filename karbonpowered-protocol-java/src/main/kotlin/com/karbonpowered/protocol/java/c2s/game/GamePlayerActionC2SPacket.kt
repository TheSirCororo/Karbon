package com.karbonpowered.protocol.java.c2s.game

import com.karbonpowered.catalog.CatalogType
import com.karbonpowered.catalog.NamespacedKey
import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MagicValues
import com.karbonpowered.protocol.java.MinecraftPacket
import com.karbonpowered.protocol.java.data.BlockFace
import com.karbonpowered.protocol.java.data.Position

data class GamePlayerActionC2SPacket
constructor(
    val action: PlayerAction,
    val position: Position,
    val face: BlockFace
) : MinecraftPacket() {
    enum class PlayerAction : CatalogType {
        START_DIGGING,
        CANCEL_DIGGING,
        FINISH_DIGGING,
        DROP_ITEM_STACK,
        DROP_ITEM,
        RELEASE_USE_ITEM,
        SWAP_HANDS;

        override val key: NamespacedKey = NamespacedKey.karbon(name.toLowerCase())
    }

    object Codec : com.karbonpowered.network.codec.Codec<GamePlayerActionC2SPacket> {
        override fun decode(buffer: MessageBuf): GamePlayerActionC2SPacket {
            val action = MagicValues.key(PlayerAction::class, buffer.readVarInt())
            val position = Position.Codec1_14.decode(buffer)
            val face = MagicValues.key(BlockFace::class, buffer.readUnsignedByte())
            return GamePlayerActionC2SPacket(action, position, face)
        }

        override fun encode(buffer: MessageBuf, message: GamePlayerActionC2SPacket): MessageBuf {
            buffer.writeVarInt(MagicValues.value(message.action))
            Position.Codec1_14.encode(buffer, message.position)
            buffer.writeByte(MagicValues.value(message.face))
            return buffer
        }
    }
}