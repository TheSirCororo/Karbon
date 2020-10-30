package com.karbonpowered.commons.builder

/**
 * A common interface for all builder pattern types.
 *
 * @param T The type built by the builder
 * @param B The child builder type
 */
interface ResettableBuilder<T, B : ResettableBuilder<T, B>> : Builder<T> {
    /**
     * Resets this builder to a "default" state such that there is no
     * remaining data to set. This is to be the presumed "default"
     * state.
     *
     * @return This builder, for chaining
     */
    fun reset(): B
}