package com.github.karbonpowered.karbon.profile

import com.github.karbonpowered.api.profile.property.ProfileProperty
import com.karbonpowered.data.persistence.DataContainer

data class KarbonProfileProperty(
        override val name: String,
        override val value: String,
        override val signature: String?
) : ProfileProperty {
    override val contentVersion: Int = 0

    override fun toContainer(): DataContainer {
        TODO("Not yet implemented")
    }
}