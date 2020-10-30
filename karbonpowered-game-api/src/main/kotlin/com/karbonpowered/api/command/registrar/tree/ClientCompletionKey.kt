package com.karbonpowered.api.command.registrar.tree

import com.karbonpowered.catalog.CatalogType

/**
 * Represents the client-side behaviour of a command parameter.
 */
interface ClientCompletionKey<T : CommandTreeNode<T>> : CatalogType {
    /**
     * Creates a [CommandTreeNode] that represents this [ClientCompletionKey]
     *
     * @return The new [CommandTreeNode]
     */
    fun createNode(): T
}