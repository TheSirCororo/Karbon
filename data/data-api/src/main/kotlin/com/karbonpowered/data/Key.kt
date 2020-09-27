package com.karbonpowered.data

import com.google.common.reflect.TypeToken
import com.karbonpowered.catalog.CatalogBuilder
import com.karbonpowered.catalog.CatalogType
import com.karbonpowered.catalog.NamespacedKey
import com.karbonpowered.data.value.Value
import java.util.function.BiPredicate

interface Key<V : Value<*>> : CatalogType {
    val valueToken: TypeToken<V>

    val elementToken: TypeToken<*>

    val elementComparator: Comparator<*>

    val elementIncludesTester: BiPredicate<*, *>

    interface Builder<E, V: Value<E>> : CatalogBuilder<Key<V>, Builder<E,V>> {
        fun <T, B:Value<T>> type(token: TypeToken<B>): Builder<T,B>

        fun comparator(comparator: Comparator<in E>): Builder<E,V>

        fun includesTester(predicate: BiPredicate<in E, in E>): Builder<E,V>

        override fun key(key: NamespacedKey): Builder<E, V>

        override fun build(): Key<V>
    }
}