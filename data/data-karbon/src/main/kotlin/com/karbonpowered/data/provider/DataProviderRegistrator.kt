package com.karbonpowered.data.provider

import com.karbonpowered.data.DataProvider
import com.karbonpowered.data.Key
import com.karbonpowered.data.provider.generic.GenericMutableDataProvider
import com.karbonpowered.data.transaction.DataTransactionResult
import com.karbonpowered.data.value.Value
import java.util.function.*
import java.util.function.Function

open class DataProviderRegistrator(
        val builder: DataProviderRegistratorBuilder
) {
    fun <T> asMutable(target: Class<T>): MutableRegistrator<T> = MutableRegistrator(builder, target)

    class MutableRegistrator<T>(
            builder: DataProviderRegistratorBuilder,
            val target: Class<T>
    ) : DataProviderRegistrator(builder) {
        @Suppress("UNCHECKED_CAST")
        fun <K, V : Value<K>> create(key: Key<V>): MutableRegistration<T, K> {
            val registration = MutableRegistration(this, key as Key<Value<K>>)
            register(registration)
            return registration
        }

        fun <K, V : Value<K>> create(key: Key<V>, registration: MutableRegistration<T, K>.() -> Unit): MutableRegistrator<T> = apply {
            create(key).apply(registration)
        }

        fun <NE> register(registration: MutableRegistration<T, NE>): MutableRegistrator<T> = apply {
            builder.register(registration.build(target))
        }
    }

    class MutableRegistration<H, E>(
            val registrator: MutableRegistrator<H>,
            key: Key<Value<E>>
    ) : MutableRegistrationBase<H, E, MutableRegistration<H, E>>(key) {
        fun <NE> create(key: Key<Value<NE>>): MutableRegistration<H, NE> {
            val registration = MutableRegistration(registrator, key)
            registrator.register(registration)
            return registration
        }
    }

    @Suppress("UNCHECKED_CAST")
    open class MutableRegistrationBase<H, E, R : MutableRegistrationBase<H, E, R>>(
            val key: Key<Value<E>>
    ) {
        private var constructValue: BiFunction<H, E, Value<E>>? = null
        private var get: Function<H, E?>? = null
        private var setAnd: BiFunction<H, E, Boolean>? = null
        private var set: BiConsumer<H, E>? = null
        private var deleteAnd: Function<H, Boolean>? = null
        private var delete: Consumer<H>? = null
        private var deleteAndGet: Function<H, DataTransactionResult>? = null
        private var resetOnDelete: Function<H, E>? = null
        private var setAndGet: BiFunction<H, E, DataTransactionResult>? = null
        private var supports: Function<H, Boolean>? = null

        fun constructValue(constructValue: BiFunction<H, E, Value<E>>): R = apply {
            this.constructValue = constructValue
        } as R

        fun get(get: Function<H, E?>): R = apply {
            this.get = get
        } as R

        fun setAnd(setAnd: BiFunction<H, E, Boolean>): R = apply {
            this.setAnd = setAnd
        } as R

        fun delete(delete: Consumer<H>): R = apply {
            this.delete = delete
        } as R

        fun deleteAndGet(deleteAndGet: Function<H, DataTransactionResult>): R = apply {
            this.deleteAndGet = deleteAndGet
        } as R

        fun deleteAnd(deleteAnd: Function<H, Boolean>): R = apply {
            this.deleteAnd = deleteAnd
        } as R

        fun resetOnDelete(resetOnDelete: Function<H, E>): R = apply {
            this.resetOnDelete = resetOnDelete
        } as R

        fun resetOnDelete(resetOnDelete: Supplier<E>): R = apply {
            this.resetOnDelete = Function {
                resetOnDelete.get()
            }
        } as R

        fun set(set: BiConsumer<H, E>): R = apply {
            this.set = set
        } as R

        fun setAndGet(setAndGet: BiFunction<H, E, DataTransactionResult>): R = apply {
            this.setAndGet = setAndGet
        } as R

        fun supports(supports: Function<H, Boolean>): R = apply {
            this.supports = supports
        } as R

        fun build(target: Class<H>): DataProvider<*, *> {
            val registration = this
            return object : GenericMutableDataProvider<H, E>(registration.key, target) {
                val isBooleanKey = registration.key.elementToken.rawType == Boolean::class.java


                override fun constructValue(dataHolder: H, element: E): Value<E> =
                        registration.constructValue?.apply(dataHolder, element)
                                ?: super.constructValue(dataHolder, element)

                override fun getFrom(dataHolder: H): E? =
                        registration.get?.apply(dataHolder)

                override fun set(dataHolder: H, value: E): Boolean =
                        registration.setAnd?.apply(dataHolder, value)
                                ?: registration.set?.accept(dataHolder, value)?.run { true }
                                ?: super.set(dataHolder, value)

                override fun delete(dataHolder: H): Boolean =
                        registration.deleteAnd?.apply(dataHolder)
                                ?: registration.delete?.accept(dataHolder)?.run { true }
                                ?: registration.resetOnDelete?.let { set(dataHolder, it.apply(dataHolder)) }
                                ?: super.delete(dataHolder)

                override fun setAndGetResult(dataHolder: H, value: E): DataTransactionResult =
                        registration.setAndGet?.apply(dataHolder, value) ?: super.setAndGetResult(dataHolder, value)


                override fun deleteAndGetResult(dataHolder: H): DataTransactionResult =
                        registration.deleteAndGet?.apply(dataHolder)
                                ?: registration.resetOnDelete?.let { setAndGetResult(dataHolder, it.apply(dataHolder)) }
                                ?: super.deleteAndGetResult(dataHolder)

                override fun supports(dataHolder: H): Boolean =
                        registration.supports?.apply(dataHolder) ?: super.supports(dataHolder)


            }
        }
    }
}