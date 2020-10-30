package com.karbonpowered.translation.karbon

import com.karbonpowered.translation.Translation
import java.text.MessageFormat
import java.util.*
import java.util.concurrent.ConcurrentHashMap

data class KarbonTranslation(
    override val id: String
) : Translation {
    val formats = ConcurrentHashMap<Locale, MessageFormat>()

    override fun get(locale: Locale): MessageFormat = formats[locale] ?: MessageFormat(id)

    fun register(locale: Locale, format: MessageFormat) {
        if (formats.putIfAbsent(locale, format) != null) {
            throw IllegalArgumentException("Translation already exists: $id for $locale")
        }
    }
}