package com.karbonpowered.data.persistence

import com.karbonpowered.catalog.CatalogType
import com.karbonpowered.commons.Nameable
import kotlin.reflect.KClass

interface DataView : Nameable {
    val container: DataContainer

    val currentPath: DataQuery

    val parent: DataView?

    fun getKeys(deep: Boolean): Set<DataQuery>

    fun getValues(deep: Boolean): Map<DataQuery, Any>

    operator fun contains(path: DataQuery): Boolean

    fun contains(path: DataQuery, vararg paths: DataQuery): Boolean

    operator fun get(path: DataQuery): Any

    operator fun set(path: DataQuery, value: Any): DataView

    fun remove(path: DataQuery): DataView

    fun createView(path: DataQuery): DataView

    fun createView(path: DataQuery, map: Map<*, *>): DataView

    fun getView(path: DataQuery): DataView?

    fun getMap(path: DataQuery): Map<*, *>?

    fun getString(path: DataQuery): String?
    fun getChar(path: DataQuery): Char?
    fun getBoolean(path: DataQuery): Boolean?
    fun getByte(path: DataQuery): Byte?
    fun getShort(path: DataQuery): Short?
    fun getInt(path: DataQuery): Int?
    fun getLong(path: DataQuery): Long?
    fun getFloat(path: DataQuery): Float?
    fun getDouble(path: DataQuery): Double?


    fun getList(path: DataQuery): List<*>?
    fun getStringList(path: DataQuery): List<String>?
    fun getCharList(path: DataQuery): List<Char>?
    fun getBooleanList(path: DataQuery): List<Boolean>?
    fun getByteList(path: DataQuery): List<Byte>?
    fun getShortList(path: DataQuery): List<Short>?
    fun getIntList(path: DataQuery): List<Int>?
    fun getLongList(path: DataQuery): List<Long>?
    fun getFloatList(path: DataQuery): List<Float>?
    fun getDoubleList(path: DataQuery): List<Double>?
    fun getMapList(path: DataQuery): List<Map<*, *>>?
    fun getViewList(path: DataQuery): List<DataView>?

    fun <T : DataSerializable> getSerializable(path: DataQuery, clazz: KClass<T>): T? =
        getSerializable(path, clazz.java)

    fun <T : DataSerializable> getSerializable(path: DataQuery, clazz: Class<T>): T?

    fun <T : DataSerializable> getSerializableList(path: DataQuery, clazz: KClass<T>): List<T>? =
        getSerializableList(path, clazz.java)

    fun <T : DataSerializable> getSerializableList(path: DataQuery, clazz: Class<T>): List<T>?

    fun <T : Any> getObject(path: DataQuery, clazz: KClass<T>): T? = getObject(path, clazz.java)
    fun <T : Any> getObject(path: DataQuery, clazz: Class<T>): T?

    fun <T : Any> getObjectList(path: DataQuery, clazz: KClass<T>): List<T>? = getObjectList(path, clazz.java)
    fun <T : Any> getObjectList(path: DataQuery, clazz: Class<T>): List<T>?

    fun <T : CatalogType> getCatalogType(path: DataQuery, clazz: KClass<T>): T? = getCatalogType(path, clazz.java)
    fun <T : CatalogType> getCatalogType(path: DataQuery, clazz: Class<T>): T?

    fun <T : CatalogType> getCatalogTypeList(path: DataQuery, clazz: KClass<T>): List<T>? =
        getCatalogTypeList(path, clazz.java)

    fun <T : CatalogType> getCatalogTypeList(path: DataQuery, clazz: Class<T>): List<T>?

    fun copy(): DataContainer
    fun copy(safety: SafetyMode): DataContainer

    fun isEmpty(): Boolean

    val safetyMode: SafetyMode

    enum class SafetyMode {
        ALL_DATA_CLONED,
        CLONED_ON_SET,
        NO_DATA_CLONED
    }
}