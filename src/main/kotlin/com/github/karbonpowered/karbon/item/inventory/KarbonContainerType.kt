package com.github.karbonpowered.karbon.item.inventory

import com.github.karbonpowered.api.catalog.NamespacedKey
import com.github.karbonpowered.api.item.inventory.ContainerType

class KarbonContainerType(
        override val key: NamespacedKey
) : ContainerType {
    override fun hashCode(): Int = key.hashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as KarbonContainerType

        if (key != other.key) return false

        return true
    }

    override fun toString(): String = "ContainerType(key=$key)"

    companion object {
        fun generate() = sequenceOf(
                create("GENERIC_9X1"),
                create("GENERIC_9X2"),
                create("GENERIC_9X3"),
                create("GENERIC_9X4"),
                create("GENERIC_9X5"),
                create("GENERIC_9X6"),
                create("GENERIC_3X3"),
                create("ANVIL"),
                create("BEACON"),
                create("BLAST_FURNACE"),
                create("BREWING_STAND"),
                create("CRAFTING"),
                create("ENCHANTMENT"),
                create("FURNACE"),
                create("GRINDSTONE"),
                create("HOPPER"),
                create("LECTERN"),
                create("LOOM"),
                create("MERCHANT"),
                create("SHULKER_BOX"),
                create("SMITHING"),
                create("SMOKER"),
                create("CARTOGRAPHY"),
                create("STONECUTTER"),
        )

        private fun create(name: String) = name.toUpperCase() to { KarbonContainerType(NamespacedKey.minecraft(name.toLowerCase())) }
    }
}