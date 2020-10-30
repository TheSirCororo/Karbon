package com.karbonpowered.protocol.java.data

import com.karbonpowered.nbt.CompoundBinaryTag
import com.karbonpowered.nbt.readNbt
import com.karbonpowered.nbt.writeNbt
import com.karbonpowered.network.message.MessageBuf

data class Item(
    val id: Int = 0,
    val amount: Int = 1,
    val tags: CompoundBinaryTag = CompoundBinaryTag.empty()
) {
    companion object {
        fun read(buffer: MessageBuf): Item? {
            val present = buffer.readBoolean()
            if (!present) {
                return null
            }
            val id = buffer.readVarInt()
            val amount = buffer.readByte().toInt()
            val tags = buffer.readNbt()
            return Item(id, amount, tags)
        }

        fun write(buffer: MessageBuf, item: Item?) {
            buffer.writeBoolean(item != null)
            if (item != null) {
                buffer.writeVarInt(item.id)
                buffer.writeByte(item.amount)
                buffer.writeNbt(item.tags)
            }
        }
    }
}