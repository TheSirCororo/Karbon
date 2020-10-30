@file:Suppress("NOTHING_TO_INLINE")

package com.karbonpowered.nbt

import com.karbonpowered.catalog.NamespacedKey
import com.karbonpowered.commons.lang.loadService

interface StringBinaryTag : BinaryTag {
    override val type: BinaryTagType<out BinaryTag>
        get() = BinaryTagTypes.STRING

    val value: String

    interface Factory {
        fun of(value: String): StringBinaryTag

        fun of(value: NamespacedKey): StringBinaryTag = of(value.formatted())
    }

    companion object {
        @JvmStatic
        fun of(value: String): StringBinaryTag = loadService<Factory>().of(value)

        @JvmStatic
        fun of(value: NamespacedKey): StringBinaryTag = loadService<Factory>().of(value)
    }
}

inline fun StringBinaryTag(value: String): StringBinaryTag = StringBinaryTag.Companion.of(value)

inline fun StringBinaryTag(value: NamespacedKey): StringBinaryTag = StringBinaryTag.Companion.of(value)

inline fun String.toBinaryTag(): StringBinaryTag = StringBinaryTag.Companion.of(this)

inline fun NamespacedKey.toBinaryTag(): StringBinaryTag = com.karbonpowered.nbt.StringBinaryTag.Companion.of(this)