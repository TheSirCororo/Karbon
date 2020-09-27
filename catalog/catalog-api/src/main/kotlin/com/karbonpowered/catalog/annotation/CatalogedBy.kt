package com.karbonpowered.catalog.annotation

import com.karbonpowered.catalog.Catalog
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class CatalogedBy(
        val catalog: KClass<out Catalog<*>>
)