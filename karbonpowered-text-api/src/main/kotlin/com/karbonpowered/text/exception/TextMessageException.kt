package com.karbonpowered.text.exception

import com.karbonpowered.text.Text
import com.karbonpowered.text.serializer.TextSerializers

open class TextMessageException(
    val text: Text? = null,
    throwable: Throwable? = null
) : Exception(throwable) {
    constructor(throwable: Throwable?) : this(null, throwable)

    override val message: String?
        get() = text?.let { TextSerializers.PLAIN.serialize(it) }

    override fun getLocalizedMessage(): String = message.toString()

    companion object {
        private const val serialVersionUID = -5281221645176698853L
    }
}