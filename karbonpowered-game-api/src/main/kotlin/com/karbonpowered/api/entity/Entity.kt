package com.karbonpowered.api.entity

import com.karbonpowered.api.world.Locatable
import com.karbonpowered.commons.Identifiable
import com.karbonpowered.math.vector.Vector3d
import com.karbonpowered.text.Text
import com.karbonpowered.translation.Translatable

interface Entity : Identifiable, Locatable, Translatable {
    val type: EntityType<*>
    var position: Vector3d
    val rotation: Vector3d
    var displayName: Text
    var passengers: MutableList<Entity>
    var vehicle: Entity?
    var baseVehicle: Entity?
    var onGround: Boolean
}