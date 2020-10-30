package com.karbonpowered.text.serializer

object TextSerializers {
    @JvmField
    val JSON = JsonTextSerializer.instance

    @JvmField
    val LEGACY = LegacyTextSerializer.instance

    @JvmField
    val PLAIN = PlainTextSerializer.instance
}