package com.karbonpowered.commons

enum class Tristate(
    private val value: Boolean
) {
    TRUE(true) {
        override fun and(other: Tristate): Tristate = if (other == FALSE) FALSE else TRUE
        override fun or(other: Tristate): Tristate = TRUE
        override fun not(other: Tristate): Tristate = FALSE
    },
    FALSE(false) {
        override fun and(other: Tristate): Tristate = FALSE
        override fun or(other: Tristate): Tristate = if (other == TRUE) TRUE else FALSE
        override fun not(other: Tristate): Tristate = TRUE
    },
    UNDEFINED(false) {
        override fun and(other: Tristate): Tristate = other
        override fun or(other: Tristate): Tristate = other
        override fun not(other: Tristate): Tristate = UNDEFINED
    };

    abstract infix fun and(other: Tristate): Tristate

    abstract infix fun or(other: Tristate): Tristate

    abstract infix fun not(other: Tristate): Tristate

    fun asBoolean(): Boolean = value

    fun asNullableBoolean(): Boolean? = if (this == UNDEFINED) null else value

    companion object {
        @JvmStatic
        fun fromNullableBoolean(value: Boolean?) = when (value) {
            true -> TRUE
            false -> FALSE
            null -> UNDEFINED
        }

        @JvmStatic
        fun fromBoolean(value: Boolean) = if (value) TRUE else FALSE
    }
}

@JvmSynthetic
fun Boolean.asTristate(): Tristate = Tristate.fromBoolean(this)

@JvmSynthetic
fun Boolean?.asTristate(): Tristate = Tristate.fromNullableBoolean(this)