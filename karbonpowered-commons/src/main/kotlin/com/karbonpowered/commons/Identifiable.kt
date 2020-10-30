package com.karbonpowered.commons

import java.util.*

/**
 * An identifiable object has a UUID that can be retrieved.
 */
interface Identifiable {
    /**
     * Gets the unique ID for this object.
     *
     * @return The [UUID]
     */
    val uniqueId: UUID
}