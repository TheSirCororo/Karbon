package com.github.karbonpowered.karbon.profile

import com.github.karbonpowered.api.profile.GameProfile
import com.github.karbonpowered.api.profile.property.ProfileProperty
import java.util.*
import kotlin.collections.HashMap

class KarbonGameProfile(
        override val uniqueId: UUID,
        override val name: String,
        override val propertyMap: MutableMap<String, ProfileProperty> = HashMap()
) : GameProfile