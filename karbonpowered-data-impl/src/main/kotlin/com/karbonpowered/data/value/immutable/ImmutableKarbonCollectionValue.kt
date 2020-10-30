package com.karbonpowered.data.value.immutable

import com.karbonpowered.data.value.CollectionValue
import java.util.function.Consumer
import java.util.function.Function
import java.util.function.Predicate

@Suppress("UNCHECKED_CAST")
abstract class ImmutableKarbonCollectionValue<
        E,
        C : Collection<E>,
        V : MutableCollection<E>,
        I : CollectionValue.Immutable<E, C, I, M>,
        M : CollectionValue.Mutable<E, C, M, I>> :
    AbstractImmutableKarbonValue<C>(),
    CollectionValue.Immutable<E, C, I, M> {
    override val size: Int get() = element.size

    override fun isEmpty(): Boolean = element.isEmpty()

    override fun contains(element: E): Boolean = this.element.contains(element)

    override fun containsAll(iterable: Iterable<E>): Boolean =
        if (iterable is Collection<*>) this.element.containsAll(iterable)
        else iterable.all { contains(it) }

    override fun getAll(): C = get()

    protected abstract fun modifyCollection(consumer: Consumer<V>): I

    override fun withElement(element: E): I = modifyCollection { it.add(element) }

    override fun withAll(elements: Iterable<E>): I = modifyCollection { it.addAll(elements) }

    override fun without(element: E): I =
        if (!contains(element)) this as I
        else modifyCollection { it.remove(element) }

    override fun withoutAll(elements: Iterable<E>): I =
        if (elements.none { contains(it) }) this as I
        else modifyCollection { it.removeAll(elements) }

    override fun withoutAll(predicate: Predicate<E>) =
        modifyCollection { it.removeIf(predicate) }

    override fun transform(function: Function<C, C>): I = with(function.apply(get()))

    override fun iterator(): Iterator<E> = get().iterator()

}