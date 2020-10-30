package com.karbonpowered.data.value.mutable

import com.karbonpowered.data.key.KarbonKey
import com.karbonpowered.data.value.CollectionValue
import java.util.function.Consumer
import java.util.function.Function
import java.util.function.Predicate

@Suppress("UNCHECKED_CAST")
abstract class MutableKarbonCollectionValue<
        E,
        C : Collection<E>,
        V : MutableCollection<E>,
        M : CollectionValue.Mutable<E, C, M, I>,
        I : CollectionValue.Immutable<E, C, I, M>
        > : AbstractMutableKarbonValue<C>(), CollectionValue.Mutable<E, C, M, I> {
    abstract override val key: KarbonKey<M, C>

    override val size: Int get() = element.size

    override fun isEmpty(): Boolean = element.isEmpty()

    override fun contains(element: E): Boolean = this.element.contains(element)

    override fun containsAll(iterable: Iterable<E>): Boolean =
        if (iterable is Collection<*>) element.containsAll(iterable) else iterable.all { contains(it) }

    override fun getAll(): C = element

    protected abstract fun modifyCollection(consumer: Consumer<V>): M

    override fun add(element: E): M = modifyCollection { it.add(element) }

    override fun addAll(elements: Iterable<E>): M = modifyCollection { it.addAll(elements) }

    override fun remove(element: E): M = if (!contains(element)) this as M else modifyCollection { it.remove(element) }

    override fun removeAll(elements: Iterable<E>): M =
        if (elements.none { element.contains(it) }) this as M else modifyCollection { elements.forEach { remove(it) } }

    override fun removeAll(predicate: Predicate<E>): M = modifyCollection { it.removeIf(predicate) }

    override fun set(value: C): M = super.set(value) as M

    override fun transform(function: Function<C, C>): M = set(function.apply(get()))

    override fun iterator(): Iterator<E> = element.iterator()
}