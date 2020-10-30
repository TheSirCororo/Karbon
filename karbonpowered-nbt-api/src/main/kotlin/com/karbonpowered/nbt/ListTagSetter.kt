package com.karbonpowered.nbt

interface ListTagSetter<R, T : BinaryTag> {
    fun add(tag: T): R
}