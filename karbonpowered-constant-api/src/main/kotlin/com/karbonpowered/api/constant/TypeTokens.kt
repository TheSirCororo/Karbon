package com.karbonpowered.api.constant

import com.google.common.reflect.TypeToken
import com.karbonpowered.api.profile.GameProfile
import com.karbonpowered.api.profile.property.ProfileProperty
import com.karbonpowered.data.value.ListValue
import com.karbonpowered.data.value.Value
import com.karbonpowered.text.Text

object TypeTokens {
    val BOOLEAN_VALUE_TOKEN = object : TypeToken<Value<Boolean>>() {}
    val INT_VALUE_TOKEN = object : TypeToken<Value<Int>>() {}
    val TEXT_VALUE_TOKEN = object : TypeToken<Value<Text>>() {}
    val LIST_TEXT_VALUE_TOKEN = object : TypeToken<ListValue<Text>>() {}
    val GAME_PROFILE_VALUE_TOKEN = object : TypeToken<Value<GameProfile>>() {}
    val PROFILE_PROPERTY_VALUE_TOKEN = object : TypeToken<Value<ProfileProperty>>() {}
}