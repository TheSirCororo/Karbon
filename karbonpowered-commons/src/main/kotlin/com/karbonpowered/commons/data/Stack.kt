package com.karbonpowered.commons.data

class Stack<T>() : Collection<T> {
    private val items = arrayListOf<T>()

    override val size: Int get() = items.size
    override fun isEmpty() = size == 0

    constructor(vararg items: T) : this() {
        for (item in items) push(item)
    }

    fun push(v: T) = run { items.add(v) }
    fun pop(): T = items.removeAt(items.size - 1)
    fun peek(): T? = items.lastOrNull()

    override fun contains(element: T): Boolean = items.contains(element)
    override fun containsAll(elements: Collection<T>): Boolean = items.containsAll(elements)
    override fun iterator(): Iterator<T> = items.iterator()

    override fun hashCode(): Int = items.hashCode()
    override fun equals(other: Any?): Boolean = (other is Stack<*>) && items == other.items
}