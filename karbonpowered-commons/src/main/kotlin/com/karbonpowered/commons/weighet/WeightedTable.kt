package com.karbonpowered.commons.weighet

import kotlin.random.Random

class WeightedTable<T> : RandomObjectTable<T> {
    private var totalWeight = 0.0

    constructor(rolls: VariableAmount) : super(rolls)
    constructor(rolls: Int = 1) : super(rolls)

    override fun add(obj: T, weight: Double): Boolean {
        val added = super.add(obj, weight)
        if (added) {
            recalculateWeight()
        }
        return added
    }

    override fun add(element: TableEntry<T>): Boolean {
        val added = super.add(element)
        if (added) {
            recalculateWeight()
        }
        return added
    }

    override fun addAll(elements: Collection<TableEntry<T>>): Boolean {
        val added = super.addAll(elements)
        if (added) {
            recalculateWeight()
        }
        return added
    }

    override fun remove(element: TableEntry<T>): Boolean {
        val removed = super.remove(element)
        if (removed) {
            recalculateWeight()
        }
        return removed
    }

    override fun removeAll(elements: Collection<TableEntry<T>>): Boolean {
        val removed = super.removeAll(elements)
        if (removed) {
            recalculateWeight()
        }
        return removed
    }

    override fun retainAll(elements: Collection<TableEntry<T>>): Boolean {
        val removed = super.retainAll(elements)
        if (removed) {
            recalculateWeight()
        }
        return removed
    }

    override fun clear() {
        super.clear()
        recalculateWeight()
    }

    fun recalculateWeight() {
        totalWeight = 0.0
        val iterator = iterator()
        while (iterator.hasNext()) {
            val entry = iterator.next()
            if (entry.weight < 0) {
                iterator.remove()
            } else {
                totalWeight += entry.weight
            }
        }
    }

    override fun get(random: Random): List<T> {
        if (isEmpty()) {
            return emptyList()
        }
        val results = ArrayList<T>()
        val rolls = rolls.getFlooredAmount(random)
        repeat(rolls) {
            var roll = random.nextDouble() * totalWeight
            iterator().forEach { next ->
                roll -= next.weight
                if (roll <= 0) {
                    if (next is NestedTableEntry<T>) {
                        results.addAll(next[random])
                    } else if (next is WeightedObject<T>) {
                        results.add(next.get())
                    }
                    return@repeat
                }
            }
        }
        return results
    }

    override fun iterator(): MutableIterator<TableEntry<T>> {
        val iterator = super.iterator()
        return object : MutableIterator<TableEntry<T>> {
            override fun hasNext(): Boolean = iterator.hasNext()

            override fun next(): TableEntry<T> = iterator.next()

            override fun remove() {
                iterator.remove()
                recalculateWeight()
            }
        }
    }
}