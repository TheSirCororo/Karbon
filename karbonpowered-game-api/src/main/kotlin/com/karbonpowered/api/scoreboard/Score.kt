package com.karbonpowered.api.scoreboard

import com.karbonpowered.api.scoreboard.objective.Objective
import com.karbonpowered.commons.Nameable

interface Score : Nameable {
    override val name: String
    var score: Int
    val objectives: Set<Objective>
}