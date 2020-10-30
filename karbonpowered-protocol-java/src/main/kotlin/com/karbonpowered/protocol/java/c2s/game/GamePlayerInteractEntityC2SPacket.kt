package com.karbonpowered.protocol.java.c2s.game

import com.karbonpowered.api.entity.living.humanoid.player.hand.HandType
import com.karbonpowered.api.entity.living.humanoid.player.hand.HandTypes
import com.karbonpowered.network.codec.Codec
import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MagicValues
import com.karbonpowered.protocol.java.MinecraftPacket
import com.karbonpowered.protocol.java.data.InteractAction

data class GamePlayerInteractEntityC2SPacket
@JvmOverloads
constructor(
    val entityId: Int = 0,
    val action: InteractAction = InteractAction.INTERACT,
    val targetX: Float = 0.0f,
    val targetY: Float = 0.0f,
    val targetZ: Float = 0.0f,
    val hand: HandType = HandTypes.MAIN_HAND
) : MinecraftPacket() {
    object Codec1_9 : Codec<GamePlayerInteractEntityC2SPacket> {
        override fun decode(buffer: MessageBuf): GamePlayerInteractEntityC2SPacket {
            val entityId = buffer.readVarInt()
            val action = MagicValues.key(InteractAction::class, buffer.readVarInt())
            var targetX = 0.0f
            var targetY = 0.0f
            var targetZ = 0.0f
            if (action == InteractAction.INTERACT_AT) {
                targetX = buffer.readFloat()
                targetY = buffer.readFloat()
                targetZ = buffer.readFloat()
            }
            var hand = HandTypes.MAIN_HAND
            if (action != InteractAction.ATTACK) {
                hand = MagicValues.key(HandType::class, buffer.readVarInt())
            }
            return GamePlayerInteractEntityC2SPacket(entityId, action, targetX, targetY, targetZ, hand)
        }

        override fun encode(buffer: MessageBuf, message: GamePlayerInteractEntityC2SPacket): MessageBuf {
            buffer.writeVarInt(message.entityId)
            buffer.writeVarInt(MagicValues.value(message.action))
            if (message.action == InteractAction.INTERACT_AT) {
                buffer.writeFloat(message.targetX)
                buffer.writeFloat(message.targetY)
                buffer.writeFloat(message.targetZ)
            }
            if (message.action != InteractAction.ATTACK) {
                buffer.writeVarInt(MagicValues.value(message.hand))
            }
            return buffer
        }
    }

    object Codec1_8 : Codec<GamePlayerInteractEntityC2SPacket> {
        override fun decode(buffer: MessageBuf): GamePlayerInteractEntityC2SPacket {
            val entityId = buffer.readVarInt()
            val action = MagicValues.key(InteractAction::class, buffer.readVarInt())
            var targetX = 0.0f
            var targetY = 0.0f
            var targetZ = 0.0f
            if (action == InteractAction.INTERACT_AT) {
                targetX = buffer.readFloat()
                targetY = buffer.readFloat()
                targetZ = buffer.readFloat()
            }
            return GamePlayerInteractEntityC2SPacket(entityId, action, targetX, targetY, targetZ)
        }

        override fun encode(buffer: MessageBuf, message: GamePlayerInteractEntityC2SPacket): MessageBuf {
            buffer.writeVarInt(message.entityId)
            buffer.writeVarInt(MagicValues.value(message.action))
            if (message.action == InteractAction.INTERACT_AT) {
                buffer.writeFloat(message.targetX)
                buffer.writeFloat(message.targetY)
                buffer.writeFloat(message.targetZ)
            }
            return buffer
        }
    }

    object Codec1_7 : Codec<GamePlayerInteractEntityC2SPacket> {
        override fun decode(buffer: MessageBuf): GamePlayerInteractEntityC2SPacket {
            val entityId = buffer.readVarInt()
            val action = MagicValues.key(InteractAction::class, buffer.readByte())
            var targetX = 0.0f
            var targetY = 0.0f
            var targetZ = 0.0f
            if (action == InteractAction.INTERACT_AT) {
                targetX = buffer.readFloat()
                targetY = buffer.readFloat()
                targetZ = buffer.readFloat()
            }
            return GamePlayerInteractEntityC2SPacket(entityId, action, targetX, targetY, targetZ)
        }

        override fun encode(buffer: MessageBuf, message: GamePlayerInteractEntityC2SPacket): MessageBuf {
            buffer.writeVarInt(message.entityId)
            buffer.writeByte(MagicValues.value(message.action))
            if (message.action == InteractAction.INTERACT_AT) {
                buffer.writeFloat(message.targetX)
                buffer.writeFloat(message.targetY)
                buffer.writeFloat(message.targetZ)
            }
            return buffer
        }
    }
}