package com.karbonpowered.commons.builder

import java.util.function.Consumer

interface Buildable<R, B : CopyableBuilder<R, B>> {
    fun toBuilder(): B

    companion object {
        @JvmStatic
        fun <R : Buildable<R, B>, B : CopyableBuilder<R, B>> build(builder: B, consumer: Consumer<B>): R {
            consumer.accept(builder)
            return builder.build()
        }
    }
}