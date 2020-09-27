package com.karbonpowered.data.provider.generic

import com.karbonpowered.data.Key
import com.karbonpowered.data.value.Value
import java.util.function.Supplier

abstract class GenericMutableDataProvider<H, E> : GenericMutableDataProviderBase<H, Value<E>, E> {
    constructor(key: Key<Value<E>>) : super(key)
    constructor(key: Supplier<Key<Value<E>>>) : this(key.get())
    constructor(key: Key<Value<E>>, holderType: Class<H>) : super(key, holderType)
    constructor(key: Supplier<Key<Value<E>>>, holderType: Class<H>) : this(key.get(), holderType)
}
