package com.karbonpowered.translation

import com.karbonpowered.translation.locale.Locales
import java.text.MessageFormat

class TranslationRegistryTest {
    companion object {
        val REGISTRY = TranslationRegistry
    }

    fun testRegister() {
        REGISTRY.registerAll(
            Locales.EN_US,
            "https://raw.githubusercontent.com/KyoriPowered/adventure/master/api/src/test/resources/adventure-test_en_US.properties",
            true
        )
        REGISTRY.register("what", Locales.RU_RU, MessageFormat("Что???", Locales.RU_RU))
        REGISTRY.registerAll(Locales.RU_RU, "karbon-test", true)
    }
}

fun main() {
    val test = TranslationRegistryTest()
    test.testRegister()

    println(TranslationRegistry.translate("what", Locales.RU_RU).format(emptyArray<Any>()))
    println(TranslationRegistry.translate("cats", Locales.RU_RU).format(emptyArray<Any>()))
    println(TranslationRegistry.translate("cats", Locales.EN_US).format(emptyArray<Any>()))
}