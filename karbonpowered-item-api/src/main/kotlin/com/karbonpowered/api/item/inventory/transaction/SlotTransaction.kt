package com.karbonpowered.api.item.inventory.transaction

import com.karbonpowered.api.item.inventory.ItemStack
import com.karbonpowered.api.item.inventory.ItemStackSnapshot
import com.karbonpowered.api.item.inventory.Slot
import com.karbonpowered.data.transaction.Transaction

open class SlotTransaction(
    val slot: Slot, original: ItemStackSnapshot, defaultReplacement: ItemStackSnapshot
) : Transaction<ItemStackSnapshot>(original, defaultReplacement) {
    fun setCustom(stack: ItemStack) {
        custom = stack.createSnapshot()
    }

    override fun toString(): String = "SlotTransaction(slot=$slot) ${super.toString()}"
}