package com.karbonpowered.api.item.inventory.transaction

import com.karbonpowered.api.item.inventory.ItemStack
import com.karbonpowered.api.item.inventory.ItemStackSnapshot
import com.karbonpowered.commons.builder.CopyableBuilder
import com.karbonpowered.commons.lang.loadService

interface InventoryTransactionResult {
    val type: Type
    val rejectedItems: List<ItemStackSnapshot>
    val polledItems: List<ItemStackSnapshot>
    val slotTransactions: List<SlotTransaction>

    fun and(other: InventoryTransactionResult): InventoryTransactionResult

    fun revert()

    fun revertOnFailure(): Boolean

    interface Pool : InventoryTransactionResult {
        val pooledItem: ItemStackSnapshot
    }

    interface Builder : CopyableBuilder<InventoryTransactionResult, Builder> {
        fun type(type: Type): Builder

        fun reject(vararg itemStacks: ItemStack): Builder
        fun reject(itemStacks: Iterable<ItemStack>): Builder
        fun reject(itemStacks: Iterator<ItemStack>): Builder
        fun reject(itemStacks: Collection<ItemStack>): Builder

        fun pool(itemStack: ItemStackSnapshot): PoolBuilder

        fun transaction(vararg slotTransactions: SlotTransaction): Builder
        fun transaction(slotTransactions: Iterable<SlotTransaction>): Builder
        fun transaction(slotTransactions: Iterator<SlotTransaction>): Builder
        fun transaction(slotTransactions: Collection<SlotTransaction>): Builder

        override fun build(): InventoryTransactionResult

        interface PoolBuilder : Builder {
            override fun build(): Pool
        }
    }

    enum class Type {
        SUCCESS, FAILURE, ERROR, NO_SLOT
    }

    companion object {
        fun builder(): Builder = loadService()

        fun successNoTransactions() = builder().type(Type.SUCCESS).build()

        fun failNoTransactions() = builder().type(Type.ERROR).build()
    }
}