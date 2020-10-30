package com.karbonpowered.api.scoreboard.objective

import com.karbonpowered.api.scoreboard.Score
import com.karbonpowered.api.scoreboard.Scoreboard
import com.karbonpowered.api.scoreboard.criteria.Criterion
import com.karbonpowered.api.scoreboard.objective.displaymode.ObjectiveDisplayMode
import com.karbonpowered.commons.Nameable
import com.karbonpowered.commons.builder.CopyableBuilder
import com.karbonpowered.commons.lang.loadService
import com.karbonpowered.text.Text

interface Objective : Nameable {
    override val name: String
    var displayName: Text
    val criterion: Criterion
    var displayMode: ObjectiveDisplayMode
    val scores: Map<Text, Score>
    val scoreboard: Set<Scoreboard>

    fun hasScore(name: Text): Boolean
    operator fun contains(name: Text): Boolean = hasScore(name)

    fun addScore(score: Score)

    fun getScore(name: Text): Score? = if (hasScore(name)) getOrCreateScore(name) else null

    fun getOrCreateScore(name: Text): Score

    fun removeScore(score: Score): Boolean

    fun removeScore(name: Text): Boolean

    interface Builder : CopyableBuilder<Objective, Builder> {
        var name: String
        var displayName: Text
        var criterion: Criterion
        var objectiveDisplayMode: ObjectiveDisplayMode
    }

    companion object {
        fun builder(): Builder = loadService()
    }
}