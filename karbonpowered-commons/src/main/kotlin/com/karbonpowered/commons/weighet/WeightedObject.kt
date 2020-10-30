package com.karbonpowered.commons.weighet

class WeightedObject<T>(
    private val obj: T,
    weight: Double
) : TableEntry<T>(weight) {
    fun get(): T = obj
}