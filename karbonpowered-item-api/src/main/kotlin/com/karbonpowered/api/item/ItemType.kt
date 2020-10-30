package com.karbonpowered.api.item

import com.karbonpowered.catalog.CatalogType
import com.karbonpowered.catalog.annotation.CatalogedBy

@CatalogedBy(ItemTypes::class)
interface ItemType : CatalogType {
    val container: ItemType?
    val maxStackQuantity: Int
}