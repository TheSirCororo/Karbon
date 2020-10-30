package com.karbonpowered.translation

import com.karbonpowered.commons.lang.loadService
import java.text.MessageFormat
import java.util.*

interface Translation {
    val id: String

    fun get(): MessageFormat = get(Locale.getDefault())

    operator fun get(locale: Locale): MessageFormat

    interface Factory {
        fun find(id: String): Translation?
    }

    companion object {
        val factory by lazy { loadService<Factory>() }

        fun find(id: String): Translation? = factory.find(id)
    }
}