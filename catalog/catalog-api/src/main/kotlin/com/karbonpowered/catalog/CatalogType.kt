package com.karbonpowered.catalog

/**
 * Represents a type of a dummy that can be used to identify types without
 * using an [Enum]
 */
interface CatalogType {
    /**
     * A catalog key for this catalog type. Useful for storing a searchable
     * id reference within the [GameRegistry]. Since the [GameRegistry]
     * can effectively search for the [NamespacedKey.namespace], a fail
     * fast scenario can be achieved if the namespace provider (usually a plugin or
     * mod) is unavailable.
     */
    val key: NamespacedKey

    override fun hashCode(): Int
}