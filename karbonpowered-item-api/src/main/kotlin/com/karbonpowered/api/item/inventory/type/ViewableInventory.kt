package com.karbonpowered.api.item.inventory.type

import com.karbonpowered.api.item.inventory.Inventory
import com.karbonpowered.api.item.inventory.menu.InventoryMenu

interface ViewableInventory : Inventory {

    val hasViewers: Boolean


    fun asMenu(): InventoryMenu
}