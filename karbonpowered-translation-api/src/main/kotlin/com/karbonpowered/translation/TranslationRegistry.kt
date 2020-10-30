package com.karbonpowered.translation

import com.karbonpowered.commons.lang.loadService
import java.text.MessageFormat
import java.util.*
import java.util.function.Function
import java.util.regex.Pattern
import kotlin.collections.ArrayList

interface TranslationRegistry : TranslationSource {
    fun register(key: String, locale: Locale, format: MessageFormat)

    fun unregister(key: String)

    @JvmDefault
    fun registerAll(locale: Locale, formats: Map<String, MessageFormat>) =
        registerAll(locale, formats.keys) { formats[it] ?: MessageFormat(it) }

    @JvmDefault
    fun registerAll(locale: Locale, keys: Set<String>, function: Function<String, MessageFormat>) {
        var errors: MutableList<IllegalArgumentException>? = null
        for (key in keys) {
            try {
                register(key, locale, function.apply(key))
            } catch (e: IllegalArgumentException) {
                if (errors == null) {
                    errors = ArrayList()
                }
                errors.add(e)
            }
        }
        if (errors != null) {
            val size = errors.size
            if (size == 1) {
                throw  errors.first()
            } else {
                throw IllegalArgumentException("Invalid key (and ${size - 1} more)", errors.first())
            }
        }
    }

    @JvmDefault
    fun registerAll(locale: Locale, resourceBundle: ResourceBundle) = registerAll(locale, resourceBundle, true)

    @JvmDefault
    fun registerAll(locale: Locale, resourceBundle: ResourceBundle, escapeSingleQuotes: Boolean) {
        registerAll(locale, resourceBundle.keySet()) {
            val format = resourceBundle.getString(it)
            MessageFormat(
                if (escapeSingleQuotes) SINGLE_QUOTE_PATTERN.matcher(format).replaceAll("''") else format,
                locale
            )
        }
    }

    @JvmDefault
    fun registerAll(locale: Locale, resourceBundlePath: String) = registerAll(locale, resourceBundlePath, true)

    @JvmDefault
    fun registerAll(locale: Locale, resourceBundlePath: String, escapeSingleQuotes: Boolean) {
        val resourceBundle = try {
            ResourceBundle.getBundle(resourceBundlePath, locale, UTF8ResourceBundleControl)
        } catch (e: MissingResourceException) {
            return
        }
        registerAll(locale, resourceBundle, escapeSingleQuotes)
    }

    companion object : TranslationRegistry by loadService() {
        private val SINGLE_QUOTE_PATTERN = Pattern.compile("'")
    }
}