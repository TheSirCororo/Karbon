package com.karbonpowered.commons.lang

import java.util.*

inline fun <reified T> loadService() = ServiceLoader.load(T::class.java).firstOrNull()
    ?: ServiceLoader.load(T::class.java, T::class.java.classLoader).firstOrNull()
    ?: error("Can't load implementation for ${T::class.java.simpleName}")