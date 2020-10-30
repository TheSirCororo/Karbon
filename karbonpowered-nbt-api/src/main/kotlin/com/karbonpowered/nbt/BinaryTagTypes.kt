package com.karbonpowered.nbt

import com.karbonpowered.catalog.Catalog
import com.karbonpowered.catalog.CatalogRegistry
import com.karbonpowered.catalog.getProvider

object BinaryTagTypes : Catalog<BinaryTagType<*>> {
    override val type: Class<BinaryTagType<*>>
        get() = BinaryTagType::class.java

    @JvmField
    val END = CatalogRegistry.getProvider<BinaryTagType<*>, BinaryTagType<EndBinaryTag>>("END").get()

    @JvmField
    val BYTE = CatalogRegistry.getProvider<BinaryTagType<*>, BinaryTagType<ByteBinaryTag>>("BYTE").get()

    @JvmField
    val SHORT = CatalogRegistry.getProvider<BinaryTagType<*>, BinaryTagType<ShortBinaryTag>>("SHORT").get()

    @JvmField
    val INT = CatalogRegistry.getProvider<BinaryTagType<*>, BinaryTagType<IntBinaryTag>>("INT").get()

    @JvmField
    val LONG = CatalogRegistry.getProvider<BinaryTagType<*>, BinaryTagType<LongBinaryTag>>("LONG").get()

    @JvmField
    val FLOAT = CatalogRegistry.getProvider<BinaryTagType<*>, BinaryTagType<FloatBinaryTag>>("FLOAT").get()

    @JvmField
    val DOUBLE = CatalogRegistry.getProvider<BinaryTagType<*>, BinaryTagType<DoubleBinaryTag>>("DOUBLE").get()

    @JvmField
    val BYTE_ARRAY =
        CatalogRegistry.getProvider<BinaryTagType<*>, BinaryTagType<ByteArrayBinaryTag>>("BYTE_ARRAY").get()

    @JvmField
    val STRING = CatalogRegistry.getProvider<BinaryTagType<*>, BinaryTagType<StringBinaryTag>>("STRING").get()

    @JvmField
    val LIST = CatalogRegistry.getProvider<BinaryTagType<*>, BinaryTagType<ListBinaryTag>>("LIST").get()

    @JvmField
    val COMPOUND = CatalogRegistry.getProvider<BinaryTagType<*>, BinaryTagType<CompoundBinaryTag>>("COMPOUND").get()

    @JvmField
    val INT_ARRAY = CatalogRegistry.getProvider<BinaryTagType<*>, BinaryTagType<IntArrayBinaryTag>>("INT_ARRAY").get()

    @JvmField
    val LONG_ARRAY =
        CatalogRegistry.getProvider<BinaryTagType<*>, BinaryTagType<LongArrayBinaryTag>>("LONG_ARRAY").get()
}