@file:Suppress("NOTHING_TO_INLINE")

package com.karbonpowered.nbt

import com.karbonpowered.commons.lang.loadService

interface EndBinaryTag : BinaryTag {
    override val type: BinaryTagType<out BinaryTag>
        get() = BinaryTagTypes.END

    companion object : EndBinaryTag by loadService()
}