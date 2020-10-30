package com.karbonpowered.api.scoreboard

import com.karbonpowered.commons.Nameable
import com.karbonpowered.commons.builder.CopyableBuilder
import com.karbonpowered.text.Text
import com.karbonpowered.text.format.TextColor

interface Team : Nameable {
    override val name: String
    val members: Set<Text>
    val scoreboard: Scoreboard

    var displayName: Text
    var color: TextColor
    var prefix: Text
    var suffix: Text
    var isAllowFriendlyFire: Boolean
    var isCanSeeFriendlyInvisibles: Boolean
    var nameTagVisibility: Visibility
    var deathMessageVisibility: Visibility
    var collisionRule: CollisionRule

    fun addMember(member: Text)

    fun removeMember(member: Text): Boolean

    fun unregister()

    interface Builder : CopyableBuilder<Team, Builder> {
        var name: String
        var color: TextColor
        var displayName: Text
        var prefix: Text
        var suffix: Text
        var isAllowFriendlyFire: Boolean
        var isCanSeeFriendlyInvisibles: Boolean
        var nameTagVisibility: Visibility
        var deathMessageVisibility: Visibility
        var collisionRule: CollisionRule
        var members: Set<Text>
    }
}