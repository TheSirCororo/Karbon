package com.karbonpowered.commons.builder

interface CopyableBuilder<T, B : ResettableBuilder<T, B>> : ResettableBuilder<T, B> {
    /**
     * Resets this builder to the values of the given built object.
     *
     * @param value The built object
     * @return This builder, for chaining
     */
    fun from(value: T): B
}