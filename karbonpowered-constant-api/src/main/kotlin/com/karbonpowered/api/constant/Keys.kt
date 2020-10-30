package com.karbonpowered.api.constant

import com.karbonpowered.api.profile.GameProfile
import com.karbonpowered.api.profile.property.ProfileProperty
import com.karbonpowered.catalog.CatalogRegistry
import com.karbonpowered.catalog.getProvider
import com.karbonpowered.data.Key
import com.karbonpowered.data.value.ListValue
import com.karbonpowered.data.value.Value
import com.karbonpowered.text.Text

object Keys {
    @JvmField
    val DISPLAY_NAME = CatalogRegistry.getProvider<Key<*>, Key<Value<Text>>>("display_name").get()

    @JvmField
    val LORE = CatalogRegistry.getProvider<Key<*>, Key<ListValue<Text>>>("lore").get()

    @JvmField
    val ITEM_DURABILITY = CatalogRegistry.getProvider<Key<*>, Key<Value<Int>>>("item_durability").get()

    @JvmField
    val HIDE_ENCHANTMENTS = CatalogRegistry.getProvider<Key<*>, Key<Value<Boolean>>>("hide_enchantments").get()

    @JvmField
    val HIDE_ATTRIBUTES = CatalogRegistry.getProvider<Key<*>, Key<Value<Boolean>>>("hide_attributes").get()

    @JvmField
    val HIDE_UNBREAKABLE = CatalogRegistry.getProvider<Key<*>, Key<Value<Boolean>>>("hide_unbreakable").get()

    @JvmField
    val HIDE_CAN_DESTROY = CatalogRegistry.getProvider<Key<*>, Key<Value<Boolean>>>("hide_can_destroy").get()

    @JvmField
    val HIDE_CAN_PLACE = CatalogRegistry.getProvider<Key<*>, Key<Value<Boolean>>>("hide_can_place").get()

    @JvmField
    val HIDE_POTION_EFFECTS = CatalogRegistry.getProvider<Key<*>, Key<Value<Boolean>>>("hide_potion_effects").get()

    @JvmField
    val HIDE_DYE = CatalogRegistry.getProvider<Key<*>, Key<Value<Boolean>>>("hide_dye").get()

    @JvmField
    val GAME_PROFILE = CatalogRegistry.getProvider<Key<*>, Key<Value<GameProfile>>>("game_profile").get()

    @JvmField
    val SKIN_PROFILE_PROPERTY =
        CatalogRegistry.getProvider<Key<*>, Key<Value<ProfileProperty>>>("skin_profile_property").get()
}