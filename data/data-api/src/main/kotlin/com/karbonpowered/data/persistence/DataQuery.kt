package com.karbonpowered.data.persistence

class DataQuery private constructor(
        val parts: List<String>
) {
    val queryParts by lazy {
        parts.map {
            of(listOf(it))
        }
    }

    fun then(that: DataQuery) = of(this.parts + that.parts)

    fun then(that: String) = of(this.parts + that)

    fun pop(): DataQuery {
        if (parts.size <= 1) {
            return EMPTY
        }
        return of(parts.subList(0, parts.size - 1))
    }

    fun popFirst(): DataQuery {
        if (parts.size <= 1) {
            return EMPTY
        }
        return of(parts.subList(1, parts.size))
    }

    fun last(): DataQuery {
        if (parts.size <= 1) {
            return this
        }
        return of(parts.last())
    }

    fun asString(separator: Char): String = parts.joinToString(separator = separator.toString())

    fun asString(separator: CharSequence): String = parts.joinToString(separator = separator)

    override fun toString(): String = asString(".")

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DataQuery

        if (parts != other.parts) return false

        return true
    }

    override fun hashCode(): Int = parts.hashCode()

    companion object {
        private val EMPTY = DataQuery(emptyList())

        @JvmStatic
        fun of(): DataQuery = EMPTY

        @JvmStatic
        fun of(separator: Char, path: String): DataQuery = DataQuery(path.split(separator))

        @JvmStatic
        fun of(vararg parts: String): DataQuery = if (parts.isNotEmpty()) DataQuery(parts.toList()) else EMPTY

        @JvmStatic
        fun of(parts: Iterable<String>): DataQuery = if (parts.iterator().hasNext()) DataQuery(parts.toList()) else EMPTY
    }
}

fun DataQuery(): DataQuery = DataQuery.of()
fun DataQuery(separator: Char, path: String): DataQuery = DataQuery.of(separator, path)
fun DataQuery(vararg parts: String): DataQuery = DataQuery.of(*parts)
fun DataQuery(parts: Iterable<String>): DataQuery = DataQuery.of(parts)