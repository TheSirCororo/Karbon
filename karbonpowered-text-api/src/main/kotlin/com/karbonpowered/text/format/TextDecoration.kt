package com.karbonpowered.text.format

import com.karbonpowered.commons.Nameable
import com.karbonpowered.commons.lang.loadService

interface TextDecoration : TextFormat, TextStyle.BuilderApplicable, Nameable {
    override fun apply(builder: TextStyle.Builder) {
        builder.decorate(this)
    }

    interface Provider {
        val values: Collection<TextDecoration>

        fun provide(name: String): TextDecoration?
    }

    companion object {
        private val provider by lazy { loadService<Provider>() }

        @JvmStatic
        @get:JvmName("values")
        val values = provider.values

        @JvmStatic
        fun of(name: String): TextDecoration? = provider.provide(name)
    }
}