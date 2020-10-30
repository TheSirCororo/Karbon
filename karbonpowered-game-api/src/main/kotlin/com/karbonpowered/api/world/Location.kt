package com.karbonpowered.api.world

import com.karbonpowered.math.vector.Vector3d
import com.karbonpowered.math.vector.Vector3i

interface Location {
    val position: Vector3d
    val blockPosition: Vector3i
    val chunkPosition: Vector3i
    val biomePosition: Vector3i

    val x: Double
    val y: Double
    val z: Double

    val blockX: Int
    val blockY: Int
    val blockZ: Int
}