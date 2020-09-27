package com.karbonpowered.data.value.mutable

import com.karbonpowered.data.key.KarbonKey
import com.karbonpowered.data.value.CollectionValue
import java.util.function.Consumer
import java.util.function.Function
import java.util.function.Predicate

abstract class MutableKarbonCollectionValue<
        E,
        C : Collection<E>,
        M : MutableCollection<E>,
        V : CollectionValue.Mutable<E, C, V, I>,
        I : CollectionValue.Immutable<E, C, I, V>
        > : AbstractMutableKarbonValue<C>(), CollectionValue.Mutable<E, C, V, I> {
    abstract override val key: KarbonKey<V, C>

    override val size: Int get() = element.size

    override fun isEmpty(): Boolean = element.isEmpty()

    override fun contains(element: E): Boolean = this.element.contains(element)

    override fun contains(iterable: Iterable<E>): Boolean = if (iterable is Collection<*>) element.containsAll(iterable) else iterable.all { contains(it) }

    override fun getAll(): C = element

    protected abstract fun modifyCollection(consumer: Consumer<M>): V

    override fun add(element: E): V = modifyCollection { it.add(element) }

    override fun addAll(elements: Iterable<E>): V = modifyCollection {  it.addAll(elements) }

    override fun remove(element: E): V = if (!contains(element)) this as V else modifyCollection {  it.remove(element) }

    override fun removeAll(elements: Iterable<E>): V = if (elements.none { element.contains(it) }) this as V else modifyCollection { elements.forEach { remove(it) } }

    override fun removeAll(predicate: Predicate<E>): V = modifyCollection {  it.removeIf(predicate) }

    override fun set(value: C): V = super.set(value) as V

    override fun transform(function: Function<C, C>): V = set(function.apply(get()))

    override fun iterator(): Iterator<E> = element.iterator()
}