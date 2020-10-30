package com.karbonpowered.api.item.inventory


interface Container : Inventory {
    fun isViewedSlot(slot: Slot): Boolean

    val viewed: List<Inventory>

    var cursor: ItemStack?

    val isOpen: Boolean

    val type: ContainerType
}