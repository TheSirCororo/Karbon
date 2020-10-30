package com.karbonpowered.data.transaction

import com.karbonpowered.commons.builder.CopyableBuilder
import com.karbonpowered.data.value.Value

class DataTransactionResult private constructor(
    val type: Type,
    val rejected: List<Value.Immutable<*>> = emptyList(),
    val replaced: List<Value.Immutable<*>> = emptyList(),
    val success: List<Value.Immutable<*>> = emptyList(),
    val exception: Throwable? = null
) {
    fun isSuccessful(): Boolean = type == Type.SUCCESS

    enum class Type {
        UNDEFINED, SUCCESS, FAILURE, ERROR, CANCELLED
    }

    class Builder : CopyableBuilder<DataTransactionResult, Builder> {
        var result: Type? = null
        var reject: MutableList<Value.Immutable<*>>? = null
        var replace: MutableList<Value.Immutable<*>>? = null
        var success: MutableList<Value.Immutable<*>>? = null
        var exception: Throwable? = null

        fun result(result: Type?) = apply {
            this.result = result
        }

        fun reject(vararg values: Value.Immutable<*>) = reject(values.asIterable())
        fun reject(values: Iterable<Value.Immutable<*>>) = apply {
            if (reject == null) {
                reject = ArrayList()
            }
            reject?.addAll(values)
        }

        fun replace(vararg values: Value.Immutable<*>) = replace(values.asIterable())
        fun replace(values: Iterable<Value.Immutable<*>>) = apply {
            if (replace == null) {
                replace = ArrayList()
            }
            replace?.addAll(values)
        }

        fun success(vararg values: Value.Immutable<*>) = success(values.asIterable())
        fun success(values: Iterable<Value.Immutable<*>>) = apply {
            if (success == null) {
                success = ArrayList()
            }
            success?.addAll(values)
        }

        fun error(exception: Exception) = apply {
            this.exception = exception
        }

        override fun build(): DataTransactionResult = DataTransactionResult(
            checkNotNull(result),
            reject ?: emptyList(),
            replace ?: emptyList(),
            success ?: emptyList(),
            exception
        )

        override fun from(value: DataTransactionResult): Builder = apply {
            result = value.type
            reject = value.rejected.toMutableList()
            replace = value.replaced.toMutableList()
            success = value.success.toMutableList()
            exception = value.exception
        }

        override fun reset(): Builder = apply {
            result = null
            replace = null
            reject = null
            success = null
            exception = null
        }
    }

    companion object {
        @JvmStatic
        fun builder(): Builder = Builder()

        private val FAIL_NODATA = builder().result(Type.FAILURE).build()
        private val SUCCESS_NODATA = builder().result(Type.SUCCESS).build()

        @JvmStatic
        fun successNoData(): DataTransactionResult = SUCCESS_NODATA

        @JvmStatic
        fun successResult(vararg value: Value.Immutable<*>): DataTransactionResult =
            builder().result(Type.SUCCESS).success(*value).build()

        @JvmStatic
        fun successReplaceResult(successful: Value.Immutable<*>, replaced: Value.Immutable<*>): DataTransactionResult =
            builder().result(Type.SUCCESS).success(successful).replace(replaced).build()

        @JvmStatic
        fun successReplaceResult(
            successful: Iterable<Value.Immutable<*>>,
            replaced: Iterable<Value.Immutable<*>>
        ): DataTransactionResult =
            builder().result(Type.SUCCESS).success(successful).replace(replaced).build()

        @JvmStatic
        fun successRemove(vararg removed: Value.Immutable<*>): DataTransactionResult =
            builder().result(Type.SUCCESS).replace(*removed).build()

        @JvmStatic
        fun successRemove(removed: Iterable<Value.Immutable<*>>): DataTransactionResult =
            builder().result(Type.SUCCESS).replace(removed).build()

        @JvmStatic
        fun failResult(vararg value: Value.Immutable<*>): DataTransactionResult =
            builder().result(Type.FAILURE).reject(*value).build()

        @JvmStatic
        fun failResult(values: Iterable<Value.Immutable<*>>): DataTransactionResult =
            builder().result(Type.FAILURE).reject(values).build()

        @JvmStatic
        fun failNoData(): DataTransactionResult = FAIL_NODATA

        @JvmStatic
        fun errorResult(exception: Exception, vararg value: Value.Immutable<*>): DataTransactionResult =
            builder().result(Type.ERROR).error(exception).reject(*value).build()

        @JvmStatic
        fun errorResult(exception: Exception, values: Iterable<Value.Immutable<*>>): DataTransactionResult =
            builder().result(Type.ERROR).error(exception).reject(values).build()
    }
}