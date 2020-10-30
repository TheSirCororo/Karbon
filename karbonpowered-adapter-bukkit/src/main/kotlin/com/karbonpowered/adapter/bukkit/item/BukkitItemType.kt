package com.karbonpowered.adapter.bukkit.item

import com.karbonpowered.api.item.ItemType
import com.karbonpowered.api.item.ItemTypes
import com.karbonpowered.catalog.CatalogRegistry
import com.karbonpowered.catalog.NamespacedKey
import org.bukkit.Material
import java.util.*

class BukkitItemType(
    val material: Material
) : ItemType {
    override val container: ItemType? = null
    override val maxStackQuantity: Int = 64
    override val key: NamespacedKey = NamespacedKey.minecraft(material.name.toLowerCase())

    override fun hashCode(): Int = material.hashCode()

    companion object {
        fun register() {
            CatalogRegistry.register(ItemType::class, Material.values().asSequence().filter {
                it.isItem || it.isBlock
            }.map {
                val bukkitItemType = BukkitItemType(it)
                itemType_bukkit[it] = bukkitItemType
                it.name.toLowerCase() to { bukkitItemType }
            })
        }
    }
}

private val itemType_bukkit = EnumMap<Material, ItemType>(Material::class.java)

fun Material.asItemType(): ItemType = itemType_bukkit.getOrPut(this) {
    ItemTypes.sequence().first { it.asBukkit() == this }
}

fun ItemType.asBukkit(): Material = (this as? BukkitItemType)?.material ?: TODO()
