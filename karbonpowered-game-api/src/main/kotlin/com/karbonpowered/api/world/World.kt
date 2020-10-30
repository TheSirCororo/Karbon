package com.karbonpowered.api.world

import com.karbonpowered.api.entity.living.humanoid.player.Player

interface World<W : World<W>> {
    val isLoaded: Boolean

    val players: Collection<Player>
}