package com.karbonpowered.api.event.cause

interface Cause {
    val causeStack: Array<Any>

    val root: Any get() = causeStack[0]
}