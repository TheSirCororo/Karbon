package com.karbonpowered.api.profile

import com.karbonpowered.api.profile.property.ProfileProperty
import com.karbonpowered.commons.lang.loadService
import java.util.*

interface GameProfileManager {
    fun createProfile(uniqueId: UUID, name: String? = null): GameProfile

    fun createProfileProperty(name: String, value: String, signature: String? = null): ProfileProperty

    companion object : GameProfileManager by loadService()
}