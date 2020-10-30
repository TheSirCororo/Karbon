package com.karbonpowered.api.world

import com.karbonpowered.math.vector.Vector3i

interface Locatable {
    val location: Location

    val blockPosition: Vector3i
        get() = location.blockPosition
}