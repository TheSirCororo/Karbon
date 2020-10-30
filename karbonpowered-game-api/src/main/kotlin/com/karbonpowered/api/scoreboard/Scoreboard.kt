package com.karbonpowered.api.scoreboard

import com.karbonpowered.api.scoreboard.criteria.Criterion
import com.karbonpowered.api.scoreboard.displayslot.DisplaySlot
import com.karbonpowered.api.scoreboard.objective.Objective
import com.karbonpowered.commons.builder.CopyableBuilder
import com.karbonpowered.commons.lang.loadService
import com.karbonpowered.text.Text
import java.util.function.Supplier

interface Scoreboard {

    val objectives: Set<Objective>
    val scores: Set<Score>
    val teams: Set<Team>

    fun getObjective(name: String): Objective?

    fun getObjective(slot: DisplaySlot): Objective?
    fun getObjective(slot: Supplier<DisplaySlot>): Objective? = getObjective(slot.get())

    fun addObjective(objective: Objective)

    fun updateDisplaySlot(objective: Objective?, displaySlot: DisplaySlot)
    fun updateDisplaySlot(objective: Objective?, displaySlot: Supplier<DisplaySlot>) = updateDisplaySlot(objective, displaySlot.get())

    fun clearSlot(displaySlot: DisplaySlot)
    fun clearSlot(displaySlot: Supplier<DisplaySlot>) = clearSlot(displaySlot.get())

    fun getObjectivesByCriterion(criterion: Criterion): Set<Objective>
    fun getObjectivesByCriterion(criterion: Supplier<Criterion>): Set<Objective> = getObjectivesByCriterion(criterion.get())

    fun removeObjective(objective: Objective)

    fun getScores(name: Text): Set<Score>

    fun removeScores(name: Text)

    fun getTeam(name: String): Team?

    fun registerTeam(team: Team)

    fun getMemberTeam(member: Text): Team?

    interface Builder : CopyableBuilder<Scoreboard, Builder> {
        var objectives: List<Objective>
        var teams: List<Team>
    }

    companion object {
        fun builder(): Builder = loadService()
    }
}