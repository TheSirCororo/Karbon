package com.github.karbonpowered.karbon.profile

import com.karbonpowered.api.profile.GameProfile
import com.karbonpowered.api.profile.GameProfileManager
import com.karbonpowered.api.profile.property.ProfileProperty
import java.util.*

class KarbonGameProfileManager : GameProfileManager {
    override fun createProfile(uniqueId: UUID, name: String?): GameProfile = KarbonGameProfile(uniqueId, name ?: "")

    override fun createProfileProperty(name: String, value: String, signature: String?): ProfileProperty =
            KarbonProfileProperty(name, value, signature)
}