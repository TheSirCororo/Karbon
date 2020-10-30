package com.karbonpowered.api.item.inventory

import com.karbonpowered.catalog.Catalog
import com.karbonpowered.catalog.CatalogRegistry

object ContainerTypes : Catalog<ContainerType> {
    override val type: Class<ContainerType>
        get() = ContainerType::class.java

    @JvmField
    val GENERIC_9X1 = CatalogRegistry.getProvider(ContainerType::class, "GENERIC_9X1").get()

    @JvmField
    val GENERIC_9X2 = CatalogRegistry.getProvider(ContainerType::class, "GENERIC_9X2").get()

    @JvmField
    val GENERIC_9X3 = CatalogRegistry.getProvider(ContainerType::class, "GENERIC_9X3").get()

    @JvmField
    val GENERIC_9X4 = CatalogRegistry.getProvider(ContainerType::class, "GENERIC_9X4").get()

    @JvmField
    val GENERIC_9X5 = CatalogRegistry.getProvider(ContainerType::class, "GENERIC_9X5").get()

    @JvmField
    val GENERIC_9X6 = CatalogRegistry.getProvider(ContainerType::class, "GENERIC_9X6").get()

    @JvmField
    val GENERIC_3X3 = CatalogRegistry.getProvider(ContainerType::class, "GENERIC_3X3").get()

    @JvmField
    val ANVIL = CatalogRegistry.getProvider(ContainerType::class, "ANVIL").get()

    @JvmField
    val BEACON = CatalogRegistry.getProvider(ContainerType::class, "BEACON").get()

    @JvmField
    val BLAST_FURNACE = CatalogRegistry.getProvider(ContainerType::class, "BLAST_FURNACE").get()

    @JvmField
    val BREWING_STAND = CatalogRegistry.getProvider(ContainerType::class, "BREWING_STAND").get()

    @JvmField
    val CRAFTING = CatalogRegistry.getProvider(ContainerType::class, "CRAFTING").get()

    @JvmField
    val ENCHANTMENT = CatalogRegistry.getProvider(ContainerType::class, "ENCHANTMENT").get()

    @JvmField
    val FURNACE = CatalogRegistry.getProvider(ContainerType::class, "FURNACE").get()

    @JvmField
    val GRINDSTONE = CatalogRegistry.getProvider(ContainerType::class, "GRINDSTONE").get()

    @JvmField
    val HOPPER = CatalogRegistry.getProvider(ContainerType::class, "HOPPER").get()

    @JvmField
    val LECTERN = CatalogRegistry.getProvider(ContainerType::class, "LECTERN").get()

    @JvmField
    val LOOM = CatalogRegistry.getProvider(ContainerType::class, "LOOM").get()

    @JvmField
    val MERCHANT = CatalogRegistry.getProvider(ContainerType::class, "MERCHANT").get()

    @JvmField
    val SHULKER_BOX = CatalogRegistry.getProvider(ContainerType::class, "SHULKER_BOX").get()

    @JvmField
    val SMITHING = CatalogRegistry.getProvider(ContainerType::class, "SMITHING").get()

    @JvmField
    val SMOKER = CatalogRegistry.getProvider(ContainerType::class, "SMOKER").get()

    @JvmField
    val CARTOGRAPHY = CatalogRegistry.getProvider(ContainerType::class, "CARTOGRAPHY").get()

    @JvmField
    val STONECUTTER = CatalogRegistry.getProvider(ContainerType::class, "STONECUTTER").get()
}