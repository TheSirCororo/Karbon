package com.karbonpowered.catalog

import com.github.karbonpowered.commons.builder.ResettableBuilder
import com.github.karbonpowered.commons.lang.loadService

interface NamespacedKey : Comparable<NamespacedKey> {
    val namespace: String
    val value: String

    fun formatted(): String

    override fun compareTo(other: NamespacedKey): Int

    interface Builder : ResettableBuilder<NamespacedKey, Builder> {
        var namespace: String
        var value: String

        fun namespace(namespace: String): Builder = apply {
            this.namespace = namespace
        }

        fun value(value: String): Builder = apply {
            this.value = value
        }
    }

    companion object {
        const val MINECRAFT_NAMESPACE = "minecraft"
        const val KARBON_NAMESPACE = "karbon"

        @JvmStatic
        fun minecraft(value: String): NamespacedKey =
                of(MINECRAFT_NAMESPACE, value)

        @JvmStatic
        fun karbon(value: String): NamespacedKey =
                of(KARBON_NAMESPACE, value)

        @JvmStatic
        fun builder(): Builder = loadService()

        @JvmStatic
        fun of(namespace: String, value: String): NamespacedKey =
                builder().apply {
                    this.namespace = namespace
                    this.value = value
                }.build()

        @JvmStatic
        fun resolve(value: String): NamespacedKey =
                builder().apply {
                    val split = value.split(":")
                    this.namespace = split[0]
                    this.value = split[1]
                }.build()
    }
}