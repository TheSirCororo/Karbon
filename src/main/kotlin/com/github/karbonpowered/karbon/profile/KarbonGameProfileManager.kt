package com.github.karbonpowered.karbon.profile

import com.github.karbonpowered.api.profile.GameProfile
import com.github.karbonpowered.api.profile.GameProfileManager
import com.github.karbonpowered.api.profile.property.ProfileProperty
import java.util.*

object KarbonGameProfileManager : GameProfileManager {
    override fun createProfile(uniqueId: UUID, name: String?): GameProfile = KarbonGameProfile(uniqueId, name ?: "")

    override fun createProfileProperty(name: String, value: String, signature: String?): ProfileProperty =
        KarbonProfileProperty(name, value, signature)
}