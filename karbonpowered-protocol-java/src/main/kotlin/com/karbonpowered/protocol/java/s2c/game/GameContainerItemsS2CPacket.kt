package com.karbonpowered.protocol.java.s2c.game

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket
import com.karbonpowered.protocol.java.data.Item
import com.karbonpowered.protocol.java.util.readItem
import com.karbonpowered.protocol.java.util.writeItem

data class GameContainerItemsS2CPacket(
    val syncId: Int,
    val items: Array<Item?>
) : MinecraftPacket() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GameContainerItemsS2CPacket

        if (syncId != other.syncId) return false
        if (!items.contentEquals(other.items)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = syncId
        result = 31 * result + items.contentHashCode()
        return result
    }

    object Codec : com.karbonpowered.network.codec.Codec<GameContainerItemsS2CPacket> {
        override fun decode(buffer: MessageBuf): GameContainerItemsS2CPacket {
            val containerId = buffer.readVarInt()
            val items = Array(buffer.readShort().toInt()) {
                buffer.readItem()
            }
            return GameContainerItemsS2CPacket(containerId, items)
        }

        override fun encode(buffer: MessageBuf, message: GameContainerItemsS2CPacket): MessageBuf {
            buffer.writeVarInt(message.syncId)
            buffer.writeShort(message.items.size)
            message.items.forEach {
                buffer.writeItem(it)
            }
            return buffer
        }
    }
}