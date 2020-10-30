package com.karbonpowered.karbon.profile

import com.karbonpowered.api.profile.GameProfile
import com.karbonpowered.api.profile.property.ProfileProperty
import java.util.*
import kotlin.collections.HashMap

class KarbonGameProfile(
    override val uniqueId: UUID,
    override val name: String,
    override val propertyMap: MutableMap<String, ProfileProperty> = HashMap()
) : GameProfile