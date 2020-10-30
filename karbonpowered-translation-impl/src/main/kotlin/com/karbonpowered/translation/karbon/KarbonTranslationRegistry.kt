package com.karbonpowered.translation.karbon

import com.karbonpowered.translation.Translation
import com.karbonpowered.translation.TranslationRegistry
import java.text.MessageFormat
import java.util.*
import java.util.concurrent.ConcurrentHashMap

open class KarbonTranslationRegistry : TranslationRegistry {
    val translations = ConcurrentHashMap<String, KarbonTranslation>()

    override fun register(key: String, locale: Locale, format: MessageFormat) {
        translations.getOrPut(key) { KarbonTranslation(key) }.register(locale, format)
    }

    override fun unregister(key: String) {
        translations.remove(key)
    }

    override fun translate(key: String): Translation = translations.getOrElse(key) { KarbonTranslation(key) }
}