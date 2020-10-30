package com.karbonpowered.api.command

import com.karbonpowered.api.event.cause.Cause

interface CommandCause {
    val cause: Cause

    val root: Any get() = cause.root
}