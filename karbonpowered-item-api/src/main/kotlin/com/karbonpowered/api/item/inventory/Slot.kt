package com.karbonpowered.api.item.inventory

import com.karbonpowered.api.item.inventory.transaction.InventoryTransactionResult

interface Slot : Inventory {
    val viewedSlot: Slot

    fun set(stack: ItemStack): InventoryTransactionResult
}