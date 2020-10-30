package com.karbonpowered.data.copy

import com.karbonpowered.commons.Copyable
import java.util.*
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.LinkedHashMap

@Suppress("UNCHECKED_CAST")
object CopyHelper {
    fun <T> copy(value: T): T = when (value) {
        is Copyable<*> -> value.copy() as T
        is Map<*, *> -> copyMap(value as Map<Any, *>) as T
        is List<*> -> copyList(value as List<*>) as T
        else -> value
    }

    fun <L : List<E>, E> copyList(list: L): L {
        val copyElements: Boolean = if (list.isEmpty()) {
            false
        } else {
            val first = list.first()
            copy(first) == first
        }
        val copy: L
        when (list.javaClass) {
            LinkedList::class.java -> {
                copy = if (copyElements) {
                    list.mapTo(LinkedList()) { copy(it) } as L
                } else {
                    LinkedList(list) as L
                }
            }
            CopyOnWriteArrayList::class.java -> {
                copy = if (copyElements) {
                    list.mapTo(CopyOnWriteArrayList()) { copy(it) } as L
                } else {
                    CopyOnWriteArrayList(list) as L
                }
            }
            else -> {
                copy = if (copyElements) {
                    list.mapTo(ArrayList(list.size)) { copy(it) } as L
                } else {
                    ArrayList(list) as L
                }
            }
        }
        return copy
    }

    fun <M : Map<K, V>, K, V> copyMap(map: M): M {
        if (map is Copyable<*>) return map.copy() as M
        val copyEntries = if (map.isEmpty()) false else {
            val firstEntry = map.entries.iterator().next()
            copy(firstEntry.key) == firstEntry.key || copy(firstEntry.value) == firstEntry.value
        }
        val type: Class<*> = map.javaClass
        return if (type == HashMap::class.java) {
            if (copyEntries) {
                val newMap = HashMap<K, V>()
                map.forEach { (key, value) ->
                    newMap[copy(key)] = copy(value)
                }
                newMap as M
            } else {
                HashMap(map) as M
            }
        } else {
            if (copyEntries) {
                val newMap = LinkedHashMap<K, V>()
                map.forEach { (key, value) ->
                    newMap[copy(key)] = copy(value)
                }
                newMap as M
            } else {
                LinkedHashMap(map) as M
            }
        }
    }
}