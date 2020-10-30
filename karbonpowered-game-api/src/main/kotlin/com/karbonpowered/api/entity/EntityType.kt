package com.karbonpowered.api.entity

import com.karbonpowered.catalog.CatalogType
import com.karbonpowered.translation.Translatable

interface EntityType<E : Entity> : CatalogType, Translatable