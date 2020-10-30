package com.karbonpowered.api.profile

import com.karbonpowered.api.profile.property.ProfileProperty
import com.karbonpowered.commons.Identifiable
import java.util.*

interface GameProfile : Identifiable {
    val name: String

    val propertyMap: MutableMap<String, ProfileProperty>

    fun addProperty(property: ProfileProperty): GameProfile = addProperty(property.name, property)

    fun addProperty(name: String, property: ProfileProperty): GameProfile = apply {
        propertyMap[name] = property
    }

    fun removeProperty(property: ProfileProperty): Boolean = removeProperty(property.name, property)

    fun removeProperty(name: String, property: ProfileProperty): Boolean = propertyMap.remove(name, property)

    companion object {
        fun of(uniqueId: UUID, name: String? = null): GameProfile = GameProfileManager.createProfile(uniqueId, name)
    }
}

fun GameProfile(uniqueId: UUID, name: String? = null): GameProfile = GameProfile.of(uniqueId, name)