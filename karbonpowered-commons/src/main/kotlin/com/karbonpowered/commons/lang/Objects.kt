package com.karbonpowered.commons.lang

inline fun hashCoder(count: Int, gen: (index: Int) -> Int): Int {
    var out = 0
    for (n in 0 until count) {
        out *= 7
        out += gen(n)
    }
    return out
}

inline fun equaler(count: Int, gen: (index: Int) -> Boolean): Boolean {
    for (n in 0 until count) if (!gen(n)) return false
    return true
}

internal inline fun <T> contentHashCode(size: Int, gen: (index: Int) -> T): Int {
    var result = 1
    for (n in 0 until size) result = 31 * result + gen(n).hashCode()
    return result
}
