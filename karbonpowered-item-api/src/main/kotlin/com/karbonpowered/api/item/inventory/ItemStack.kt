package com.karbonpowered.api.item.inventory

import com.karbonpowered.api.constant.Keys
import com.karbonpowered.api.item.ItemType
import com.karbonpowered.api.item.ItemTypes
import com.karbonpowered.commons.lang.loadService
import com.karbonpowered.data.DataHolderBuilder
import com.karbonpowered.data.SerializableDataHolder
import com.karbonpowered.text.Text

interface ItemStack : SerializableDataHolder.Mutable {
    val type: ItemType

    var quantity: Int

    fun isEmpty(): Boolean = quantity <= 0 || type == ItemTypes.AIR

    fun createSnapshot(): ItemStackSnapshot

    var displayName: Text?
        get() = get(Keys.DISPLAY_NAME)
        set(value) {
            if (value != null) {
                offer(Keys.DISPLAY_NAME, value)
            } else {
                remove(Keys.DISPLAY_NAME)
            }
        }

    var lore: List<Text>?
        get() = get(Keys.LORE)
        set(value) {
            if (value == null) {
                remove(Keys.LORE)
            } else {
                offer(Keys.LORE, value)
            }
        }

    var hideEnchantments: Boolean
        get() = get(Keys.HIDE_ENCHANTMENTS) == true
        set(value) {
            offer(Keys.HIDE_ENCHANTMENTS, value)
        }

    var hideAttributes: Boolean
        get() = get(Keys.HIDE_ATTRIBUTES) == true
        set(value) {
            offer(Keys.HIDE_ATTRIBUTES, value)
        }

    var hideUnbreakable: Boolean
        get() = get(Keys.HIDE_UNBREAKABLE) == true
        set(value) {
            offer(Keys.HIDE_UNBREAKABLE, value)
        }

    var hideCanDestroy: Boolean
        get() = get(Keys.HIDE_CAN_DESTROY) == true
        set(value) {
            offer(Keys.HIDE_CAN_DESTROY, value)
        }

    var hideCanPlace: Boolean
        get() = get(Keys.HIDE_CAN_PLACE) == true
        set(value) {
            offer(Keys.HIDE_CAN_PLACE, value)
        }

    var hidePotionEffects: Boolean
        get() = get(Keys.HIDE_POTION_EFFECTS) == true
        set(value) {
            offer(Keys.HIDE_POTION_EFFECTS, value)
        }

    var hideDye: Boolean
        get() = get(Keys.HIDE_DYE) == true
        set(value) {
            offer(Keys.HIDE_DYE, value)
        }

    interface Builder : DataHolderBuilder.Mutable<ItemStack, Builder> {
        var type: ItemType
        var quantity: Int

        fun type(type: ItemType): Builder = apply {
            this.type = type
        }

        fun quantity(quantity: Int): Builder = apply {
            this.quantity = quantity
        }
    }

    companion object {
        fun builder(): Builder = loadService()

        fun of(type: ItemType = ItemTypes.AIR, quantity: Int = 1): ItemStack =
            builder().type(type).quantity(quantity).build()

        fun empty(): ItemStack = of()
    }
}

fun ItemStack(type: ItemType = ItemTypes.AIR, quantity: Int = 1, block: ItemStack.() -> Unit = {}): ItemStack =
    ItemStack.of(type, quantity).apply(block)