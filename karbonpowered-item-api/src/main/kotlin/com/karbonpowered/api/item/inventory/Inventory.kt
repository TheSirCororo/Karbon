package com.karbonpowered.api.item.inventory

import com.karbonpowered.api.item.ItemType
import com.karbonpowered.api.item.inventory.transaction.InventoryTransactionResult
import com.karbonpowered.data.value.ValueContainer

interface Inventory : ValueContainer<Any?, Any?> {
    val parent: Inventory
    val root: Inventory
    val slots: List<Slot>
    val children: List<Inventory>
    val hasChildren: Boolean

    val freeCapacity: Int
    val totalQuantity: Int
    val capacity: Int

    fun pool(): InventoryTransactionResult.Pool

    fun pool(limit: Int): InventoryTransactionResult.Pool

    fun offer(vararg stacks: ItemStack): InventoryTransactionResult
    fun offer(stacks: Iterable<ItemStack>): InventoryTransactionResult
    fun offer(stacks: Iterator<ItemStack>): InventoryTransactionResult
    fun offer(stacks: Collection<ItemStack>): InventoryTransactionResult

    fun canFit(stack: ItemStack): Boolean

    fun poolFrom(index: Int): InventoryTransactionResult.Pool

    fun poolFrom(index: Int, limit: Int): InventoryTransactionResult.Pool

    fun peekAt(index: Int): ItemStack?

    fun offer(index: Int, stack: ItemStack): InventoryTransactionResult

    fun set(index: Int, stack: ItemStack): InventoryTransactionResult

    fun getSlot(index: Int): Slot?

    fun clear()

    operator fun contains(stack: ItemStack): Boolean

    operator fun contains(type: ItemType): Boolean

    fun containsAny(stack: ItemStack): Boolean


}