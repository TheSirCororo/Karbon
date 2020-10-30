package com.karbonpowered.protocol.java.c2s.game

import com.karbonpowered.catalog.NamespacedKey
import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket

data class GamePrepareCraftingGridC2SPacket
@JvmOverloads
constructor(
    val containerId: Int = 0,
    val recipe: NamespacedKey,
    val makeAll: Boolean = true
) : MinecraftPacket() {
    object Codec : com.karbonpowered.network.codec.Codec<GamePrepareCraftingGridC2SPacket> {
        override fun decode(buffer: MessageBuf): GamePrepareCraftingGridC2SPacket {
            val containerId = buffer.readByte().toInt()
            val recipe = NamespacedKey.resolve(buffer.readString())
            val makeAll = buffer.readBoolean()
            return GamePrepareCraftingGridC2SPacket(containerId, recipe, makeAll)
        }

        override fun encode(buffer: MessageBuf, message: GamePrepareCraftingGridC2SPacket): MessageBuf {
            buffer.writeByte(message.containerId)
            buffer.writeString(message.recipe.formatted())
            buffer.writeBoolean(message.makeAll)
            return buffer
        }
    }
}