package com.karbonpowered.text.action

import java.util.function.UnaryOperator

interface HoverActionSource<T> {
    fun asHoverAction(): HoverAction<T> = asHoverAction(UnaryOperator.identity())

    fun asHoverAction(op: UnaryOperator<T>): HoverAction<T>
}