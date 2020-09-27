package com.karbonpowered.data.value

import java.util.function.Function
import java.util.function.Predicate

interface CollectionValue<E, C : Collection<E>> : Value<C>, Iterable<E> {
    val size: Int

    fun isEmpty(): Boolean

    operator fun contains(element: E): Boolean

    operator fun contains(iterable: Iterable<E>): Boolean

    fun getAll(): C


    interface Immutable<E, C : Collection<E>, I : Immutable<E, C, I, M>, M : Mutable<E, C, M, I>>
        : Value.Immutable<C>, CollectionValue<E, C> {

        fun withElement(element: E): I

        fun withAll(elements: Iterable<E>): I

        fun without(element: E): I

        fun withoutAll(elements: Iterable<E>): I

        fun withoutAll(predicate: Predicate<E>)

        override fun with(value: C): I

        override fun transform(function: Function<C, C>): I

        override fun asMutable(): M

        override fun asMutableCopy(): M = asMutable()

        @Suppress("UNCHECKED_CAST")
        override fun asImmutable(): I = this as I
    }

    interface Mutable<E, C : Collection<E>, M : Mutable<E, C, M, I>, I : Immutable<E, C, I, M>>
        : Value.Mutable<C>, CollectionValue<E, C>, Iterable<E> {

        fun add(element: E): M

        fun addAll(elements: Iterable<E>): M

        fun remove(element: E): M

        fun removeAll(elements: Iterable<E>): M

        fun removeAll(predicate: Predicate<E>): M

        override fun set(value: C): M

        override fun transform(function: Function<C, C>): M

        override fun copy(): M

        override fun asMutableCopy(): M = copy()

        @Suppress("UNCHECKED_CAST")
        override fun asMutable(): M = this as M

        override fun asImmutable(): I
    }
}