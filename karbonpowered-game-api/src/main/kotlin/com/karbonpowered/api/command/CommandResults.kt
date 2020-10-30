package com.karbonpowered.api.command

/**
 * Common [CommandResult]s
 */
object CommandResults {
    /**
     * Indicates that the command executed successfully.
     */
    @JvmField
    val SUCCESS: CommandResult = CommandResult.builder().result(1).build()

    /**
     * Indicates that the command executed, but unable to carry out it's task.
     */
    @JvmField
    val EMPTY: CommandResult = CommandResult.builder().build()
}