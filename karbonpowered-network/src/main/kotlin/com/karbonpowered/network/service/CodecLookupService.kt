package com.karbonpowered.network.service

import com.karbonpowered.network.codec.Codec
import com.karbonpowered.network.exception.IllegalOpcodeException
import com.karbonpowered.network.message.Message
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

class CodecLookupService(
    size: Int = 0
) {
    private val messages = ConcurrentHashMap<Class<out Message>, MessageBinding<*>>()
    private val opcodes = if (size <= 0) ConcurrentHashMap<Int, MessageBinding<*>>() else null
    private val opcodeTable = if (size > 0) Array<MessageBinding<*>?>(size) { null } else null
    private val nextId = AtomicInteger(0)
    private val methodLookup = MethodHandles.lookup()
    val bindings: Collection<MessageBinding<*>>
        get() = messages.values

    @Suppress("UNCHECKED_CAST")
    fun <M : Message, C : Codec<M>> bind(
        messageClass: Class<M>,
        codecClass: Class<C>,
        opcode: Int? = null
    ): MessageBinding<M> {
        val codecRegistration = messages[messageClass]
        if (codecRegistration != null) {
            return codecRegistration as MessageBinding<M>
        }
        val codec: C = codecClass.kotlin.objectInstance ?: methodLookup.findConstructor(
            codecClass,
            MethodType.methodType(codecClass)
        ).invoke() as C
        val usingOpcode = if (opcode != null) {
            if (opcode < 0) {
                throw IllegalArgumentException("Opcode must either be null or greater than or equal to 0!")
            } else opcode
        } else {
            var id: Int
            while (true) {
                id = nextId.getAndIncrement()
                if (get(id) == null) {
                    break
                }
            }
            id
        }
        val previous = get(usingOpcode)
        if (previous != null && previous.javaClass != codecClass) {
            throw IllegalStateException("Trying to bind an opcode where one already exists. New: $codecClass Old: ${previous.javaClass}")
        }
        val binding = MessageBinding(usingOpcode, codec)
        set(usingOpcode, binding)
        messages[messageClass] = binding
        return binding
    }

    private operator fun get(opcode: Int): MessageBinding<*>? {
        return if (opcodeTable != null && opcodes == null) {
            opcodeTable[opcode]
        } else if (opcodes != null && opcodeTable == null) {
            opcodes[opcode]
        } else {
            throw IllegalStateException("One an only one codec storage system must be in use!")
        }
    }

    private operator fun set(opcode: Int, binding: MessageBinding<*>) {
        if (opcodeTable != null && opcodes == null) {
            opcodeTable[opcode] = binding
        } else if (opcodes != null && opcodeTable == null) {
            opcodes[opcode] = binding
        } else {
            throw IllegalStateException("One an only one codec storage system must be in use!")
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun find(opcode: Int): MessageBinding<*> = try {
        get(opcode) ?: throw IllegalOpcodeException("Opcode 0x${Integer.toHexString(opcode)} ($opcode) is not bound!")
    } catch (e: ArrayIndexOutOfBoundsException) {
        throw IllegalOpcodeException("Opcode 0x${Integer.toHexString(opcode)} ($opcode) is not bound!")
    }

    @Suppress("UNCHECKED_CAST")
    fun <M : Message> find(clazz: Class<M>): MessageBinding<M>? = messages[clazz] as? MessageBinding<M>

    override fun toString(): String =
        "CodecLookupService(messages=$messages, opcodes=$opcodes, opcodeTable=${opcodeTable?.contentToString()})"

    data class MessageBinding<M : Message>(
        override val opcode: Int,
        override val codec: Codec<M>
    ) : com.karbonpowered.network.message.MessageBinding<M>
}