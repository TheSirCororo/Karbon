package com.karbonpowered.nbt

import com.karbonpowered.catalog.CatalogType
import com.karbonpowered.commons.lang.loadService
import java.io.DataInput
import java.io.DataOutput
import java.util.function.Predicate

interface BinaryTagType<T : BinaryTag> : Predicate<BinaryTagType<out BinaryTag>>, CatalogType, BinaryTagReader<T>,
    BinaryTagWriter<T> {
    val opcode: Int
    val isNumeric: Boolean

    override fun test(that: BinaryTagType<out BinaryTag>): Boolean =
        this == that || (isNumeric && that.isNumeric)

    override fun read(input: DataInput): T

    override fun write(tag: T, output: DataOutput)

    interface Factory {
        fun of(opcode: Int): BinaryTagType<out BinaryTag>
    }

    companion object {
        @JvmStatic
        fun of(opcode: Int): BinaryTagType<out BinaryTag> = loadService<Factory>().of(opcode)
    }
}