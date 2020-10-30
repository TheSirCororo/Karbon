package com.karbonpowered.commons.weighet

import kotlin.random.Random

abstract class RandomObjectTable<T>(
    var rolls: VariableAmount,
    private val entries: MutableList<TableEntry<T>> = ArrayList()
) : MutableCollection<TableEntry<T>> by entries {
    constructor(rolls: Int) : this(rolls.run {
        require(rolls >= 0) { "Rolls cannot be negative" }
        VariableAmount.fixed(rolls.toDouble())
    })

    fun setRolls(rolls: Int) {
        require(rolls >= 0) { "Rolls cannot be negative" }
        this.rolls = VariableAmount.fixed(rolls.toDouble())
    }

    abstract operator fun get(random: Random): List<T>

    open fun add(obj: T, weight: Double): Boolean {
        require(weight >= 0) { "Weight cannot be negative" }
        return add(WeightedObject(obj, weight))
    }
}