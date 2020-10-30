package com.karbonpowered.protocol.java.data.chunk

import com.karbonpowered.nbt.CompoundBinaryTag

class Chunk(
    val x: Int,
    val z: Int,
    val sections: List<ChunkSection>,
    val tileEntities: List<CompoundBinaryTag>,
    val heightMaps: CompoundBinaryTag,
    val biomeData: IntArray?,
) {
    fun getSectionAt(y: Int) = sections[y / 16]

    fun getBlockAt(x: Int, y: Int, z: Int) = getSectionAt(y)[x, y, z]

    fun setBlockAt(x: Int, y: Int, z: Int, state: Int) {
        getSectionAt(y)[x, y, z] = state
    }
}