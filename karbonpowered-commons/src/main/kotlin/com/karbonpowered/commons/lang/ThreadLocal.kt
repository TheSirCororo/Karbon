@file:Suppress("NOTHING_TO_INLINE")

package com.karbonpowered.commons.lang

import java.util.function.Supplier
import kotlin.reflect.KProperty

fun <T> ThreadLocal(supplier: Supplier<T>): ThreadLocal<T> = ThreadLocal.withInitial(supplier)

inline operator fun <T> ThreadLocal<T>.getValue(thisRef: Any?, property: KProperty<*>): T = get()
inline operator fun <T> ThreadLocal<T>.setValue(thisRef: Any?, property: KProperty<*>, value: T): Unit = set(value)
