package com.karbonpowered.nbt

data class KarbonStringBinaryTag(override val value: String) : StringBinaryTag {
    class Factory : StringBinaryTag.Factory {
        override fun of(value: String): StringBinaryTag = KarbonStringBinaryTag(value)
    }
}