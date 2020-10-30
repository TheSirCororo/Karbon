package com.karbonpowered.protocol.java.c2s.game

import com.karbonpowered.catalog.CatalogType
import com.karbonpowered.catalog.NamespacedKey
import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MagicValues
import com.karbonpowered.protocol.java.MinecraftPacket

data class GamePlayerStateC2SPacket
@JvmOverloads
constructor(
    val entityId: Int = 0,
    val state: PlayerState = PlayerState.START_SNEAKING,
    val jumpBoost: Int = 0
) : MinecraftPacket() {
    enum class PlayerState : CatalogType {
        START_SNEAKING,
        STOP_SNEAKING,
        LEAVE_BED,
        START_SPRINTING,
        STOP_SPRINTING,
        START_HORSE_JUMP,
        STOP_HORSE_JUMP,
        OPEN_HORSE_INVENTORY,
        START_ELYTRA_FLYING;

        override val key: NamespacedKey = NamespacedKey.karbon(name.toLowerCase())
    }

    object Codec : com.karbonpowered.network.codec.Codec<GamePlayerStateC2SPacket> {
        override fun decode(buffer: MessageBuf): GamePlayerStateC2SPacket {
            val entityId = buffer.readVarInt()
            val state = MagicValues.key(PlayerState::class, buffer.readVarInt())
            val jumpBoost = buffer.readVarInt()
            return GamePlayerStateC2SPacket(entityId, state, jumpBoost)
        }

        override fun encode(buffer: MessageBuf, message: GamePlayerStateC2SPacket): MessageBuf {
            buffer.writeVarInt(message.entityId)
            buffer.writeVarInt(MagicValues.value(message.state))
            buffer.writeVarInt(message.jumpBoost)
            return buffer
        }
    }
}