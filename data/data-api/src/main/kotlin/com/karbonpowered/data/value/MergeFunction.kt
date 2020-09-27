package com.karbonpowered.data.value

fun interface MergeFunction<V : Value<E>, E> {
    fun merge(original: V?, replacement: V?): V

    fun andThen(that: MergeFunction<V, E>): MergeFunction<V, E> {
        val self = this
        return MergeFunction { original, replacement ->
            that.merge(self.merge(original, replacement), replacement)
        }
    }

    companion object {
        @JvmStatic
        fun <V : Value<E>, E> replacementPreferred(): MergeFunction<V, E> = MergeFunction { original, replacement ->
            replacement ?: requireNotNull(original) { "Original and replacement cannot be null!" }
        }

        @JvmStatic
        fun <V : Value<E>, E> originalPreferred(): MergeFunction<V, E> = MergeFunction { original, replacement ->
            original ?: requireNotNull(replacement) { "Replacement and original cannot be null!" }
        }
    }
}
