package com.karbonpowered.text.builder

fun interface TextBuilderApplicable {
    fun apply(builder: TextBuilder<*, *>)
}