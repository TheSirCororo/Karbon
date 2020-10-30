package com.karbonpowered.text.action

import com.karbonpowered.commons.Nameable
import com.karbonpowered.text.TextElement
import com.karbonpowered.text.format.TextStyle

interface TextAction<R> : TextElement, Nameable, TextStyle.BuilderApplicable {
    val result: R
}