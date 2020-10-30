package com.karbonpowered.commons.weighet

import kotlin.random.Random

class ChanceTable<T>(rolls: Int = 1) : RandomObjectTable<T>(rolls) {
    override fun get(random: Random): List<T> {
        if (isEmpty()) {
            return emptyList()
        }
        val results = ArrayList<T>()
        val rolls = rolls.getFlooredAmount(random)
        repeat(rolls) {
            iterator().forEach { next ->
                if (random.nextDouble() < next.weight) {
                    if (next is NestedTableEntry<T>) {
                        results.addAll(next[random])
                    } else if (next is WeightedObject<T>) {
                        results.add(next.get())
                    }
                }
            }
        }
        return results
    }
}