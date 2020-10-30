package com.karbonpowered.api.event

import com.karbonpowered.api.event.cause.Cause

interface Event {
    val cause: Cause
}