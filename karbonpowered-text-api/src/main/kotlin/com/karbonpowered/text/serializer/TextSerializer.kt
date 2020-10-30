package com.karbonpowered.text.serializer

import com.karbonpowered.text.Text

interface TextSerializer<I : Text, O : Text, R> {
    fun deserialize(input: R): O

    fun serialize(text: I): R
}