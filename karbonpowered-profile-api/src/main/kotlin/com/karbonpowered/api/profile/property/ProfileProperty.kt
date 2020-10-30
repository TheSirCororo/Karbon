package com.karbonpowered.api.profile.property

import com.karbonpowered.api.profile.GameProfileManager
import com.karbonpowered.commons.Nameable
import com.karbonpowered.data.persistence.DataSerializable

interface ProfileProperty : DataSerializable, Nameable {
    override val name: String

    val value: String

    val signature: String?

    fun hasSignature(): Boolean = signature != null

    companion object {
        const val TEXTURES = "textures"

        fun of(name: String, value: String, signature: String? = null): ProfileProperty =
            GameProfileManager.createProfileProperty(name, value, signature)
    }
}