package com.karbonpowered.protocol.java.s2c.game

import com.karbonpowered.nbt.readNbt
import com.karbonpowered.nbt.writeNbt
import com.karbonpowered.network.codec.Codec
import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.network.message.MessageBuffers
import com.karbonpowered.network.netty.NettyMessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket
import com.karbonpowered.protocol.java.data.chunk.Chunk
import com.karbonpowered.protocol.java.data.chunk.ChunkSection
import java.util.*


data class GameChunkDataS2CPacket(
    val chunk: Chunk,
    val fullChunk: Boolean = true
) : MinecraftPacket() {
    object Codec_1_16 : Codec<GameChunkDataS2CPacket> {
        override fun decode(buffer: MessageBuf): GameChunkDataS2CPacket {
            // Chunk coordinates (block coordinate divided by 16, rounded down)
            val x = buffer.readInt()
            val z = buffer.readInt()

            // https://wiki.vg/Chunk_Format#Full_chunk
            val fullChunk = buffer.readBoolean()

            // Bitmask with bits set to 1 for every 16×16×16 chunk section whose data is included in Data
            val bitMask = BitSet(buffer.readVarInt())

            // Count of sections that are included in data
            val sectionsCount = bitMask.cardinality()

            val heightMaps = buffer.readNbt()

            // 1024 biome IDs, ordered by x then z then y, in 4×4×4 blocks. Not present if full chunk is false.
            val biomes = if (!fullChunk) null else IntArray(1024) { buffer.readInt() }

            // Chunk data
            val data = MessageBuffers.wrap(buffer.readByteArray())

            // Count of tile entities in chunk
            val tileEntitiesCount = buffer.readVarInt()

            // Tile entities in chunk
            val tileEntities = List(tileEntitiesCount) { buffer.readNbt() }

            val chunk = Chunk(
                x,
                z,
                List(sectionsCount) { ChunkSection.decode(data) },
                tileEntities,
                heightMaps,
                biomes
            )

            return GameChunkDataS2CPacket(chunk, fullChunk)
        }

        override fun encode(buffer: MessageBuf, message: GameChunkDataS2CPacket): MessageBuf {
            val chunk = message.chunk
            buffer.writeInt(chunk.x)
            buffer.writeInt(chunk.z)
            buffer.writeBoolean(message.fullChunk)
            var bitMask = 0
            val columnData = NettyMessageBuf()
            var sectionsCount = 0

            chunk.sections.forEachIndexed { index, section ->
                if (!section.isEmpty()) {
                    sectionsCount++
                    bitMask = bitMask or (1 shl index)
                    ChunkSection.encode(columnData, section)
                }
            }

            buffer.writeVarInt(bitMask)
            buffer.writeNbt(chunk.heightMaps)

            if (message.fullChunk) {
                val biomeData = chunk.biomeData ?: IntArray(0)

                buffer.writeVarInt(biomeData.size)
                biomeData.forEach {
                    buffer.writeInt(it)
                }
            }

            // TODO()
            // extra 2 bytes?
            buffer.writeVarInt(columnData.array().size + 2)
            buffer.writeByteArray(columnData.array())

            buffer.writeVarInt(chunk.tileEntities.size)
            chunk.tileEntities.forEach {
                buffer.writeNbt(it)
            }

            return buffer
        }
    }
}