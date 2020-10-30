package com.karbonpowered.protocol.java.data.chunk

import com.karbonpowered.commons.data.FlexibleStorage
import com.karbonpowered.commons.data.IntArrayList
import com.karbonpowered.commons.data.intArrayListOf
import com.karbonpowered.network.message.MessageBuf
import java.util.*

// Chunk section
class ChunkSection(
    // Number of non-air blocks present in the chunk section
    var blockCount: Int = 0,

    // Determines how many bits are used to encode a block
    var bitsPerEntry: Int = 4,

    // Mapping of block state IDs in the global palette to indices of section palette
    val palette: IntArrayList = intArrayListOf(AIR),

    // Block storage
    var storage: FlexibleStorage = FlexibleStorage(bitsPerEntry)
) {
    private fun index(x: Int, y: Int, z: Int): Int = y shl 8 or (z shl 4) or x

    operator fun get(x: Int, y: Int, z: Int): Int {
        val id = storage[index(x, y, z)]
        return if (bitsPerEntry <= 8) if (id >= 0 && id < palette.size) palette.getAt(id) else AIR else id
    }

    operator fun set(x: Int, y: Int, z: Int, state: Int) {
        var id = if (bitsPerEntry <= 8) palette.indexOf(state) else state
        if (id == -1) {
            palette.add(state)
            if (palette.size > 1 shl bitsPerEntry) {
                bitsPerEntry++
                val oldStates = if (bitsPerEntry > 8) ArrayList(palette) else palette
                if (bitsPerEntry > 8) {
                    palette.clear()
                    bitsPerEntry = 14
                }

                val oldStorage = storage
                storage = oldStorage.transferData(bitsPerEntry) { idx: Int ->
                    if (bitsPerEntry <= 8) oldStorage[idx] else oldStates[idx]
                }
            }

            id = if (bitsPerEntry <= 8) palette.indexOf(state) else state
        }

        val idx = index(x, y, z)
        val current = storage[idx]

        if (state != AIR && current == AIR) blockCount++
        else if (state == AIR && current != AIR) blockCount--

        storage[idx] = id
    }

    fun isEmpty(): Boolean = storage.data.all {
        it == 0L
    }

    companion object {
        private const val AIR = 0

        fun decode(buffer: MessageBuf): ChunkSection {
            val blockCount = buffer.readShort()
            val bitsPerEntry = buffer.readUnsignedByte()
            val palette = IntArrayList()
            if (bitsPerEntry <= 8) {
                val paletteLength = buffer.readVarInt()

                for (i in 0 until paletteLength) {
                    palette.add(buffer.readVarInt())
                }
            }

            val storage = FlexibleStorage(
                bitsPerEntry.toInt(),
                LongArray(buffer.readVarInt()) {
                    buffer.readLong()
                }
            )

            return ChunkSection(blockCount.toInt(), bitsPerEntry.toInt(), palette, storage)
        }

        fun encode(buffer: MessageBuf, section: ChunkSection) {
            buffer.writeShort(section.blockCount)
            buffer.writeByte(section.bitsPerEntry)
            if (section.bitsPerEntry <= 8) {
                buffer.writeVarInt(section.palette.size)
                section.palette.forEach {
                    buffer.writeVarInt(it)
                }
            }

            val data = section.storage.data
            buffer.writeVarInt(data.size)
            data.forEach {
                buffer.writeLong(it)
            }
        }
    }
}
