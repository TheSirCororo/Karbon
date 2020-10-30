package com.karbonpowered.api.scoreboard

import com.karbonpowered.catalog.Catalog
import com.karbonpowered.catalog.CatalogRegistry

object CollisionRules : Catalog<CollisionRule> {
    override val type: Class<CollisionRule>
        get() = CollisionRule::class.java

    @JvmField
    val ALWAYS = CatalogRegistry.getProvider(CollisionRule::class, "ALWAYS").get()

    @JvmField
    val NEVER = CatalogRegistry.getProvider(CollisionRule::class, "NEVER").get()

    @JvmField
    val PUSH_OTHER_TEAMS = CatalogRegistry.getProvider(CollisionRule::class, "PUSH_OTHER_TEAMS").get()

    @JvmField
    val PUSH_OWN_TEAM = CatalogRegistry.getProvider(CollisionRule::class, "PUSH_OWN_TEAM").get()
}