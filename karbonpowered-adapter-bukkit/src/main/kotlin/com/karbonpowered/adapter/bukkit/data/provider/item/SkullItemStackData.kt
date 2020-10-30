package com.karbonpowered.adapter.bukkit.data.provider.item

import com.destroystokyo.paper.profile.ProfileProperty
import com.karbonpowered.adapter.bukkit.item.inventory.BukkitItemStack
import com.karbonpowered.api.constant.Keys
import com.karbonpowered.api.profile.GameProfile
import com.karbonpowered.data.provider.DataProviderRegistrator
import org.bukkit.Bukkit
import org.bukkit.inventory.meta.SkullMeta
import java.util.*

object SkullItemStackData {
    fun register(registrator: DataProviderRegistrator) {
        registrator.asMutable(BukkitItemStack::class.java)
            .create(Keys.GAME_PROFILE) {
                get {
                    val itemMeta = it.itemStack.itemMeta as SkullMeta
                    val profile = itemMeta.playerProfile
                    val uuid = profile?.id ?: UUID.nameUUIDFromBytes("OfflinePlayer:${profile?.name}".toByteArray())
                    if (profile != null) GameProfile.of(uuid, profile.name) else null
                }
                set { item, value ->
                    val itemMeta = item.itemStack.itemMeta as SkullMeta
                    itemMeta.playerProfile = Bukkit.createProfile(value.uniqueId, value.name).apply {
                        val properties = value.propertyMap.values.map { property ->
                            ProfileProperty(property.name, property.value, property.signature)
                        }
                        setProperties(properties)
                    }
                    item.itemStack.itemMeta = itemMeta
                }
                supports {
                    it.itemStack.itemMeta is SkullMeta
                }
            }
    }
}