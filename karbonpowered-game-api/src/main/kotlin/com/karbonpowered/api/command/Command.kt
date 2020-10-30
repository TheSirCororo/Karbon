package com.karbonpowered.api.command

import com.karbonpowered.api.command.exception.CommandException
import com.karbonpowered.api.command.registrar.tree.ClientCompletionKeys
import com.karbonpowered.api.command.registrar.tree.CommandTreeNode

interface Command {
    /**
     * Execute the command based on input arguments.
     *
     * * The implementing class must perform the necessary permission checks. *
     *
     * @param cause [CommandCause] of the invocation of command
     * @param arguments The raw arguments for this command
     * @return The result of a command being processed
     */
    @Throws(CommandException::class)
    fun process(cause: CommandCause, arguments: String): CommandResult

    /**
     * Gets a list of suggestions based on input
     *
     * * If a suggestion is chosen by user it will replace the last word. *
     */
    @Throws(CommandException::class)
    fun getSuggestions(cause: CommandCause, arguments: String): List<String>

    interface Raw : Command {
        fun commandTree(): CommandTreeNode.Root =
                CommandTreeNode.root()
                        .executable()
                        .child("arguments",
                                ClientCompletionKeys.STRING.createNode()
                                        .greedy().executable().customSuggestions()
                        )
    }
}