package com.karbonpowered.translation

import java.text.MessageFormat
import java.util.*

class FixedTranslation(
    val value: MessageFormat
) : Translation {
    constructor(value: String) : this(MessageFormat(value))

    override val id: String = value.toPattern()

    override fun get(locale: Locale): MessageFormat = value
}