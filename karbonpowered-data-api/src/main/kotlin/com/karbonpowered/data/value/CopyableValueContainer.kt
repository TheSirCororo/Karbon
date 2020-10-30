package com.karbonpowered.data.value

interface CopyableValueContainer : ValueContainer<Any?, Any?> {
    fun copy(): CopyableValueContainer
}