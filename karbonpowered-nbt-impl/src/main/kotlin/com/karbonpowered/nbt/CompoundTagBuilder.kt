package com.karbonpowered.nbt

class CompoundTagBuilder : CompoundBinaryTag.Builder {
    private var tags: MutableMap<String, BinaryTag>? = null

    override fun reset(): CompoundBinaryTag.Builder = apply {
        tags?.clear()
    }

    override fun build(): CompoundBinaryTag {
        if (tags.isNullOrEmpty()) return CompoundBinaryTag.empty()
        return KarbonCompoundBinaryTag(HashMap(tags))
    }

    override fun put(key: String, tag: BinaryTag): CompoundBinaryTag.Builder = apply {
        if (tags == null) {
            tags = HashMap()
        }
        tags?.put(key, tag)
    }
}