package com.karbonpowered.commons

interface Copyable<T : Copyable<T>> {
    fun copy(): T
}