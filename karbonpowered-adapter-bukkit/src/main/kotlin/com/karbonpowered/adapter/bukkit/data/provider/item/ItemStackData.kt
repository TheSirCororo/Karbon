package com.karbonpowered.adapter.bukkit.data.provider.item

import com.karbonpowered.adapter.bukkit.item.inventory.BukkitItemStack
import com.karbonpowered.api.constant.Keys
import com.karbonpowered.data.provider.DataProviderRegistrator
import com.karbonpowered.text.serializer.bungee.asBungee
import com.karbonpowered.text.serializer.bungee.asKarbon
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.Damageable
import org.bukkit.inventory.meta.ItemMeta

object ItemStackData {
    fun register(registrator: DataProviderRegistrator) {
        registrator.asMutable(BukkitItemStack::class.java)
            .create(Keys.ITEM_DURABILITY) {
                get { item ->
                    item.itemStack as Damageable
                    item.itemStack.damage
                }
                set { item, value ->
                    item.itemStack as Damageable
                    item.itemStack.damage = value
                }
                supports { item ->
                    item.itemStack is Damageable
                }
            }
            .create(Keys.LORE) {
                get { item ->
                    item.itemStack.itemMeta.loreComponents?.map { it.asKarbon() } ?: emptyList()
                }
                set { item, value ->
                    item.itemStack.itemMeta {
                        loreComponents = value.map { it.asBungee() }
                    }
                }
                delete { item ->
                    item.itemStack.itemMeta {
                        loreComponents = null
                    }
                }
            }
            .create(Keys.DISPLAY_NAME) {
                get { item ->
                    item.itemStack.itemMeta.displayNameComponent.asKarbon()
                }
                set { item, value ->
                    item.itemStack.itemMeta {
                        setDisplayNameComponent(value.asBungee())
                    }
                }
                delete { item ->
                    item.itemStack.itemMeta {
                        setDisplayNameComponent(null)
                    }
                }
            }
            .create(Keys.HIDE_ATTRIBUTES) {
                get { item -> item.itemStack[ItemFlag.HIDE_ATTRIBUTES] }
                set { item, value -> item.itemStack[ItemFlag.HIDE_ATTRIBUTES] = value }
            }
            .create(Keys.HIDE_CAN_DESTROY) {
                get { item -> item.itemStack[ItemFlag.HIDE_DESTROYS] }
                set { item, value -> item.itemStack[ItemFlag.HIDE_DESTROYS] = value }
            }
            .create(Keys.HIDE_CAN_PLACE) {
                get { item -> item.itemStack[ItemFlag.HIDE_PLACED_ON] }
                set { item, value -> item.itemStack[ItemFlag.HIDE_PLACED_ON] = value }
            }
            .create(Keys.HIDE_ENCHANTMENTS) {
                get { item -> item.itemStack[ItemFlag.HIDE_ENCHANTS] }
                set { item, value -> item.itemStack[ItemFlag.HIDE_ENCHANTS] = value }
            }
            .create(Keys.HIDE_POTION_EFFECTS) {
                get { item -> item.itemStack[ItemFlag.HIDE_POTION_EFFECTS] }
                set { item, value -> item.itemStack[ItemFlag.HIDE_POTION_EFFECTS] = value }
            }
            .create(Keys.HIDE_UNBREAKABLE) {
                get { item -> item.itemStack[ItemFlag.HIDE_UNBREAKABLE] }
                set { item, value -> item.itemStack[ItemFlag.HIDE_UNBREAKABLE] = value }
            }
            .create(Keys.HIDE_DYE) {
                get { item -> item.itemStack[ItemFlag.HIDE_DYE] }
                set { item, value -> item.itemStack[ItemFlag.HIDE_DYE] = value }
            }
    }

    private operator fun ItemStack.get(itemFlag: ItemFlag): Boolean = itemFlags.contains(itemFlag)
    private operator fun ItemStack.set(itemFlag: ItemFlag, value: Boolean) =
        if (value) addItemFlags(itemFlag) else removeItemFlags(itemFlag)

    fun ItemStack.itemMeta(meta: ItemMeta.() -> Unit) {
        itemMeta = itemMeta.apply(meta)
    }
}