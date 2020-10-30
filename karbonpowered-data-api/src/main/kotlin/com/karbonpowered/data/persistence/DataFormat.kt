package com.karbonpowered.data.persistence

import com.karbonpowered.catalog.CatalogType
import java.io.InputStream
import java.io.OutputStream

interface DataFormat : CatalogType {
    fun read(input: InputStream): DataContainer

    fun write(output: OutputStream, data: DataView)
}