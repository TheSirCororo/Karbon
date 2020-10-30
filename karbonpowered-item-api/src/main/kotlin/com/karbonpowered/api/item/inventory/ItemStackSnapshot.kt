package com.karbonpowered.api.item.inventory

import com.karbonpowered.api.item.ItemType
import com.karbonpowered.commons.lang.loadService
import com.karbonpowered.data.persistence.DataSerializable
import com.karbonpowered.translation.Translatable

interface ItemStackSnapshot : Translatable, DataSerializable {
    val type: ItemType
    val quantity: Int
    val isEmpty: Boolean
    fun createStack(): ItemStack

    interface Factory {
        fun empty(): ItemStackSnapshot
    }

    companion object {
        fun empty(): ItemStackSnapshot = loadService<Factory>().empty()
    }
}