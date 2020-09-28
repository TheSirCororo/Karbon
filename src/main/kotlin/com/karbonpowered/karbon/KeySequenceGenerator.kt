package com.karbonpowered.karbon

import com.google.common.reflect.TypeToken
import com.karbonpowered.api.constant.TypeTokens
import com.karbonpowered.catalog.NamespacedKey
import com.karbonpowered.data.Key
import com.karbonpowered.data.key.KarbonKeyBuilder
import com.karbonpowered.data.value.Value

object KeySequenceGenerator {
    fun generate() = sequenceOf(
            key(NamespacedKey.karbon("display_name"), TypeTokens.TEXT_VALUE_TOKEN),
            key(NamespacedKey.karbon("lore"), TypeTokens.LIST_TEXT_VALUE_TOKEN),
            key(NamespacedKey.karbon("item_durability"), TypeTokens.INT_VALUE_TOKEN),
            key(NamespacedKey.karbon("hide_enchantments"), TypeTokens.BOOLEAN_VALUE_TOKEN),
            key(NamespacedKey.karbon("hide_attributes"), TypeTokens.BOOLEAN_VALUE_TOKEN),
            key(NamespacedKey.karbon("hide_unbreakable"), TypeTokens.BOOLEAN_VALUE_TOKEN),
            key(NamespacedKey.karbon("hide_can_destroy"), TypeTokens.BOOLEAN_VALUE_TOKEN),
            key(NamespacedKey.karbon("hide_can_place"), TypeTokens.BOOLEAN_VALUE_TOKEN),
            key(NamespacedKey.karbon("hide_potion_effects"), TypeTokens.BOOLEAN_VALUE_TOKEN),
            key(NamespacedKey.karbon("hide_dye"), TypeTokens.BOOLEAN_VALUE_TOKEN),
            key(NamespacedKey.karbon("game_profile"), TypeTokens.GAME_PROFILE_VALUE_TOKEN),
            key(NamespacedKey.karbon("skin_profile_property"), TypeTokens.PROFILE_PROPERTY_VALUE_TOKEN),
    )

    fun <E, V : Value<E>> key(key: NamespacedKey, token: TypeToken<V>): Pair<String, () -> Key<V>> {
        val builder = KarbonKeyBuilder<E, V>()
        return key.value to { builder.key(key).type(token).build() }
    }
}