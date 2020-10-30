package com.karbonpowered.data.transaction

import com.karbonpowered.data.persistence.DataContainer
import com.karbonpowered.data.persistence.DataQuery
import com.karbonpowered.data.persistence.DataSerializable
import com.karbonpowered.data.persistence.Queries

open class Transaction<T : DataSerializable>(
    val original: T,
    val default: T,
    val intermediary: List<T> = emptyList()
) : DataSerializable {
    var isValid: Boolean = true
    var custom: T? = null

    fun getFinal(): T = custom ?: default

    override val contentVersion: Int get() = 1

    override fun toContainer(): DataContainer = DataContainer {
        this[Queries.CONTENT_VERSION] = contentVersion
        this[TYPE_CLASS] = original.javaClass.name
        this[ORIGINAL] = original
        this[DEFAULT_REPLACEMENT] = default
        this[VALID] = isValid
        custom?.let { custom ->
            this[CUSTOM_REPLACEMENT] = custom
        }
    }

    companion object {
        private val TYPE_CLASS = DataQuery("type_class")
        private val ORIGINAL = DataQuery("original")
        private val DEFAULT_REPLACEMENT = DataQuery("default_replacement")
        private val VALID = DataQuery("is_valid")
        private val CUSTOM_REPLACEMENT = DataQuery("custom_replacement")
    }
}