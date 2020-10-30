package com.karbonpowered.nbt

import java.io.DataOutput

fun interface BinaryTagWriter<T : BinaryTag> {
    fun write(tag: T, output: DataOutput)
}