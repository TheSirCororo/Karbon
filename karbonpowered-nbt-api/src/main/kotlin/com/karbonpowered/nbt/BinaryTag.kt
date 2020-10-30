package com.karbonpowered.nbt

interface BinaryTag {
    val type: BinaryTagType<out BinaryTag>
}