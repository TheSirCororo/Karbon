package com.karbonpowered.api.entity.living.humanoid.player.modelpart

import com.karbonpowered.catalog.Catalog
import com.karbonpowered.catalog.CatalogRegistry

object SkinParts : Catalog<SkinPart> {
    override val type: Class<SkinPart>
        get() = SkinPart::class.java

    @JvmField
    val CAPE = CatalogRegistry.getProvider(SkinPart::class, "CAPE").get()

    @JvmField
    val HAT = CatalogRegistry.getProvider(SkinPart::class, "HAT").get()

    @JvmField
    val JACKET = CatalogRegistry.getProvider(SkinPart::class, "JACKET").get()

    @JvmField
    val LEFT_SLEEVE = CatalogRegistry.getProvider(SkinPart::class, "LEFT_SLEEVE").get()

    @JvmField
    val RIGHT_SLEEVE = CatalogRegistry.getProvider(SkinPart::class, "RIGHT_SLEEVE").get()

    @JvmField
    val LEFT_PANTS_LEG = CatalogRegistry.getProvider(SkinPart::class, "LEFT_PANTS_LEG").get()

    @JvmField
    val RIGHT_PANTS_LEG = CatalogRegistry.getProvider(SkinPart::class, "RIGHT_PANTS_LEG").get()

}