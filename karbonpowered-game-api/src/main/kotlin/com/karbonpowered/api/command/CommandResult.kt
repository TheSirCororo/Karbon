package com.karbonpowered.api.command

import com.karbonpowered.commons.builder.ResettableBuilder
import com.karbonpowered.commons.lang.loadService
import com.karbonpowered.text.Text

/**
 * Represents the result of a command.
 */
interface CommandResult {
    /**
     * Gets whether the command executed successfully.
     */
    val isSuccess: Boolean

    /**
     * Gets the integer result returned by the command that executed.
     */
    val result: Int

    /**
     * If present, returns the error message associated with this result.
     */
    val errorMessage: Text?

    /**
     * Builds [CommandResult]
     */
    interface Builder : ResettableBuilder<CommandResult, Builder> {
        /**
         * Sets an integer value that indicated the states of the command.
         *
         * * A positive value indicates successful execution.
         * * Zero indicated the command didn't fulfil it's task.
         * * A negative value is undefined in the API, if returned, the effects are implementation specific.
         */
        var result: Int

        /**
         * Sets or removes the error message to return to the user without throwing an exception.
         *
         * * If this is set, then the command parser will send this message to the command invoker. *
         */
        var error: Text?

        fun result(result: Int): Builder = apply {
            this.result = result
        }

        fun error(errorMessage: Text?): Builder = apply {
            this.error = error
        }
    }

    companion object {
        /**
         * Creates a builder that creates [CommandResult]s.
         */
        @JvmStatic
        fun builder(): Builder = loadService()

        /**
         * Builds a result that indicates successes.
         */
        @JvmStatic
        fun success(): CommandResult = CommandResults.SUCCESS

        /**
         * Builds an empty result.
         */
        @JvmStatic
        fun empty(): CommandResult = CommandResults.EMPTY

        /**
         * Builds an empty result that will prompt the command manager to send an
         * error message without throwing an exception.
         */
        @JvmStatic
        fun error(errorMessage: Text): CommandResult = builder().error(errorMessage).build()
    }
}