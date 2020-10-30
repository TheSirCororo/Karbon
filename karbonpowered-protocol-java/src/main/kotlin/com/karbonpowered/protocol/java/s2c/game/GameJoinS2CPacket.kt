package com.karbonpowered.protocol.java.s2c.game

import com.karbonpowered.api.entity.living.humanoid.player.gamemode.GameMode
import com.karbonpowered.catalog.NamespacedKey
import com.karbonpowered.nbt.CompoundBinaryTag
import com.karbonpowered.nbt.readNbt
import com.karbonpowered.nbt.writeNbt
import com.karbonpowered.network.codec.Codec
import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MagicValues
import com.karbonpowered.protocol.java.MinecraftPacket

data class GameJoinS2CPacket(
    val entityId: Int,
    val isHardcore: Boolean,
    val gameMode: GameMode,
    val previousGameMode: GameMode,
    val worlds: List<NamespacedKey>,
    val dimensionCodec: CompoundBinaryTag,
    val dimension: CompoundBinaryTag,
    val world: NamespacedKey,
    val hashedSeed: Long,
    val maxPlayers: Int,
    val viewDistance: Int,
    val reducedDebugInfo: Boolean,
    val enableRespawnScreen: Boolean,
    val isDebug: Boolean,
    val isFlat: Boolean
) : MinecraftPacket() {
    object Codec_1_16_2 : Codec<GameJoinS2CPacket> {
        override fun decode(buffer: MessageBuf): GameJoinS2CPacket {
            val entityId = buffer.readInt()
            val isHardcore = buffer.readBoolean()
            val gameMode = MagicValues.key(GameMode::class, buffer.readByte())
            val previousGameMode = MagicValues.key(GameMode::class, buffer.readByte())
            val worlds = List(buffer.readVarInt()) {
                NamespacedKey.resolve(buffer.readString())
            }
            val dimensionCodec = buffer.readNbt()
            val dimension = buffer.readNbt()
            val world = NamespacedKey.resolve(buffer.readString())
            val hashedSeed = buffer.readLong()
            val maxPlayers = buffer.readVarInt()
            val viewDistance = buffer.readVarInt()
            val reducedDebugInfo = buffer.readBoolean()
            val enableRespawnScreen = buffer.readBoolean()
            val isDebug = buffer.readBoolean()
            val isFlat = buffer.readBoolean()
            return GameJoinS2CPacket(
                entityId,
                isHardcore,
                gameMode,
                previousGameMode,
                worlds,
                dimensionCodec,
                dimension,
                world,
                hashedSeed,
                maxPlayers,
                viewDistance,
                reducedDebugInfo,
                enableRespawnScreen,
                isDebug,
                isFlat
            )
        }

        override fun encode(buffer: MessageBuf, message: GameJoinS2CPacket): MessageBuf {
            buffer.writeInt(message.entityId)
            buffer.writeBoolean(message.isHardcore)
            buffer.writeByte(MagicValues.value(Int::class, message.gameMode))
            buffer.writeByte(MagicValues.value(Int::class, message.previousGameMode))
            buffer.writeVarInt(message.worlds.size)
            message.worlds.forEach {
                buffer.writeString(it.formatted())
            }
            buffer.writeNbt(message.dimensionCodec)
            buffer.writeNbt(message.dimension)
            buffer.writeString(message.world.formatted())
            buffer.writeLong(message.hashedSeed)
            buffer.writeVarInt(message.maxPlayers)
            buffer.writeVarInt(message.viewDistance)
            buffer.writeBoolean(message.reducedDebugInfo)
            buffer.writeBoolean(message.enableRespawnScreen)
            buffer.writeBoolean(message.isDebug)
            buffer.writeBoolean(message.isFlat)
            return buffer
        }
    }
}