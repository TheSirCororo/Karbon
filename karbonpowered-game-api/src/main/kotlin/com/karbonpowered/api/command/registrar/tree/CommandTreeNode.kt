package com.karbonpowered.api.command.registrar.tree

import com.karbonpowered.api.command.CommandCause
import com.karbonpowered.commons.lang.loadService
import java.util.function.Predicate

interface CommandTreeNode<T : CommandTreeNode<T>> {
    fun child(key: String, childNode: CommandTreeNode.Argument<*>): T

    fun redirect(redirectTarget: CommandTreeNode<*>): T

    fun requires(requirement: Predicate<CommandCause>): T

    fun executable(): T

    fun customSuggestions(): T

    /**
     * [CommandTreeNode] that acts as the root of a command.
     */
    interface Root : CommandTreeNode<Root>

    /**
     * [CommandTreeNode] that can be used as child.
     */
    interface Argument<T : CommandTreeNode<T>> : CommandTreeNode<T>

    /**
     * [CommandTreeNode] with no known properties to set.
     */
    interface Literal : Argument<Literal>

    /**
     * [CommandTreeNode] that allow for a min-max range to be set.
     */
    interface Range<S : Number> : Argument<Range<S>> {
        /**
         * Minimum [S] acceptable for this parameter.
         */
        var min: S?

        /**
         * Maximum [S] acceptable for this parameter.
         */
        var max: S?

        fun min(min: S?): Range<S> = apply {
            this.min = min
        }

        fun max(max: S?): Range<S> = apply {
            this.max = max
        }
    }

    /**
     * [CommandTreeNode] that controls whether multiple entries
     * can be parsed.
     */
    interface Amount : Argument<Amount> {
        /**
         * Indicates that only one object can be selected by this parameter.
         * If this is not called, this element will default to allowing the
         * selection of multiple objects.
         */
        fun single(): Amount
    }

    /**
     * [CommandTreeNode] that augments entity based parameters.
     */
    interface EntitySelection : Argument<EntitySelection> {
        /**
         * Indicates that only players can be selected. If not called, all
         * entities may selected by this element.
         */
        fun playersOnly(): EntitySelection

        /**
         * Indicates that only one entity can be selected by this parameter.
         * If this is not called, this element will default to allowing the
         * selection of multiple entities.
         */
        fun single(): EntitySelection
    }

    /**
     * [CommandTreeNode] that allows for the setting of how a string
     * based parser will behave. By default, this parser will parse the next
     * word, or, if quoted, the quoted string.
     */
    interface StringParser : Argument<StringParser> {
        /**
         * Causes this string parser to only parse the next word.
         */
        fun word(): StringParser

        /**
         * Causes this string parser to parse the rest of the supplied string.
         */
        fun greedy(): StringParser
    }

    interface NodeFactory {
        /**
         * Creates a root [CommandTreeNode]
         */
        fun createRoot(): CommandTreeNode<Root>

        /**
         * Creates a literal [CommandTreeNode]
         */
        fun createLiteral(): CommandTreeNode<Literal>
    }

    companion object {
        @JvmStatic
        fun root(): CommandTreeNode<Root> = loadService<NodeFactory>().createRoot()

        @JvmStatic
        fun literal(): CommandTreeNode<Literal> = loadService<NodeFactory>().createLiteral()
    }
}