package com.karbonpowered.commons.weighet

abstract class TableEntry<T>(
    val weight: Double
) {
    init {
        require(weight >= 0) { "Weight cannot be negative" }
    }
}