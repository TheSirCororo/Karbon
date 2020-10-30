package com.karbonpowered.adapter.bukkit.item.inventory

import com.karbonpowered.adapter.bukkit.item.asBukkit
import com.karbonpowered.adapter.bukkit.item.asItemType
import com.karbonpowered.data.Key
import com.karbonpowered.data.value.Value
import com.karbonpowered.item.inventory.KarbonItemStack
import org.bukkit.inventory.ItemStack

class BukkitItemStack(
    val itemStack: ItemStack
) : KarbonItemStack(itemStack.type.asItemType(), itemStack.amount) {
    override var quantity: Int
        get() = itemStack.amount
        set(value) {
            itemStack.amount = value
        }

    open class Builder : KarbonItemStack.Builder() {
        @Suppress("UNCHECKED_CAST")
        override fun build(): com.karbonpowered.api.item.inventory.ItemStack {
            val itemStack = BukkitItemStack(ItemStack(type.asBukkit(), quantity))
            keyValues?.forEach { key, value -> itemStack.offer(key as Key<Value<Any>>, value) }
            return itemStack
        }
    }

    fun asBukkit() = itemStack
}

fun ItemStack.asKarbon(): com.karbonpowered.api.item.inventory.ItemStack =
    BukkitItemStack(this)

fun com.karbonpowered.api.item.inventory.ItemStack.asBukkit(): ItemStack =
    (this as? BukkitItemStack)?.itemStack ?: TODO()