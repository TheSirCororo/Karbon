package com.karbonpowered.commons.weighet

import kotlin.random.Random

class NestedTableEntry<T>(
    weight: Double,
    val table: RandomObjectTable<T>
) : TableEntry<T>(weight) {
    operator fun get(random: Random): List<T> = table[random]
}