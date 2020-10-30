package com.karbonpowered.protocol.java.c2s.game

import com.karbonpowered.api.entity.living.humanoid.player.hand.HandType
import com.karbonpowered.api.entity.living.humanoid.player.hand.HandTypes
import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MagicValues
import com.karbonpowered.protocol.java.MinecraftPacket

data class GamePlayerSwingArmC2SPacket
@JvmOverloads
constructor(
    val hand: HandType = HandTypes.MAIN_HAND
) : MinecraftPacket() {
    object Codec : com.karbonpowered.network.codec.Codec<GamePlayerSwingArmC2SPacket> {
        override fun decode(buffer: MessageBuf): GamePlayerSwingArmC2SPacket {
            val hand = MagicValues.key(HandType::class, buffer.readVarInt())
            return GamePlayerSwingArmC2SPacket(hand)
        }

        override fun encode(buffer: MessageBuf, message: GamePlayerSwingArmC2SPacket): MessageBuf {
            buffer.writeVarInt(MagicValues.value(message.hand))
            return buffer
        }
    }
}