package com.github.karbonpowered.karbon.item.inventory

import com.karbonpowered.api.item.ItemType
import com.karbonpowered.api.item.ItemTypes
import com.karbonpowered.api.item.inventory.ItemStack
import com.karbonpowered.api.item.inventory.ItemStackSnapshot
import com.karbonpowered.data.Key
import com.karbonpowered.data.SerializableDataHolder
import com.karbonpowered.data.holder.KarbonMutableDataHolder
import com.karbonpowered.data.persistence.DataContainer
import com.karbonpowered.data.persistence.DataQuery
import com.karbonpowered.data.persistence.DataView
import com.karbonpowered.data.persistence.Queries
import com.karbonpowered.data.value.Value

open class KarbonItemStack(
        override val type: ItemType,
        override var quantity: Int
) : ItemStack, KarbonMutableDataHolder {
    override fun createSnapshot(): ItemStackSnapshot {
        TODO("Not yet implemented")
    }

    override fun setRawData(container: DataView) {
        TODO("Not yet implemented")
    }

    override fun copy(): SerializableDataHolder.Mutable {
        TODO("Not yet implemented")
    }

    override fun validateRawData(container: DataView): Boolean = false

    override val contentVersion: Int
        get() = 1

    override fun toContainer(): DataContainer = DataContainer {
        this[Queries.CONTENT_VERSION] = contentVersion
        this[ITEM_TYPE] = type.key
        this[QUANTITY] = quantity
    }

    companion object {
        val ITEM_TYPE = DataQuery("item_type")
        val QUANTITY = DataQuery("quantity")
    }

    open class Builder : ItemStack.Builder {
        override var type: ItemType = ItemTypes.AIR
        override var quantity: Int = 1
        var keyValues: LinkedHashMap<Key<*>, Any>? = null

        override fun <V> add(key: Key<Value<V>>, value: V) {
            if (keyValues == null) keyValues = LinkedHashMap()
            keyValues?.put(key, value as Any)
        }

        override fun from(value: ItemStack): ItemStack.Builder = apply {
            this.type = value.type
            this.quantity = value.quantity
            this.keyValues = value.values.map { it.key to it }.toMap(LinkedHashMap())
        }

        @Suppress("UNCHECKED_CAST")
        override fun build(): ItemStack {
            val itemStack = KarbonItemStack(type, quantity)
            keyValues?.forEach { key, value -> itemStack.offer(key as Key<Value<Any>>, value) }
            return itemStack
        }

        override fun reset(): ItemStack.Builder = apply {
            type = ItemTypes.AIR
            quantity = 1
            keyValues = null
        }
    }
}