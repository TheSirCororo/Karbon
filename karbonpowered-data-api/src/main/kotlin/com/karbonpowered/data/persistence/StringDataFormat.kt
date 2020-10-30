package com.karbonpowered.data.persistence

import java.io.Reader
import java.io.Writer

interface StringDataFormat : DataFormat {
    fun read(input: String): DataContainer

    fun read(input: Reader): DataContainer

    fun write(data: DataView): String

    fun write(output: Writer, data: DataView)
}