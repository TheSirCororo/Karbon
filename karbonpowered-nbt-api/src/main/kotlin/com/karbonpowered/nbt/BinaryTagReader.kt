package com.karbonpowered.nbt

import java.io.DataInput

fun interface BinaryTagReader<T : BinaryTag> {
    fun read(input: DataInput): T
}