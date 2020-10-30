package com.karbonpowered.commons.function

import java.util.function.BiFunction
import java.util.function.Function

operator fun <T, R> Function<T, R>.invoke(t: T): R = apply(t)
operator fun <T, U, R> BiFunction<T, U, R>.invoke(t: T, u: U): R = apply(t, u)
