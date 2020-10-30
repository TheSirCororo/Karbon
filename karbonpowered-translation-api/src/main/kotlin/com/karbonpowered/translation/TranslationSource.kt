package com.karbonpowered.translation

import java.text.MessageFormat
import java.util.*

fun interface TranslationSource {
    fun translate(key: String, locale: Locale): MessageFormat = translate(key)[locale]
    fun translate(key: String): Translation
}