package com.karbonpowered.nbt

import com.karbonpowered.catalog.NamespacedKey
import java.io.DataInput
import java.io.DataOutput

data class KarbonBinaryTagType<T : BinaryTag>(
    override val opcode: Int,
    override val isNumeric: Boolean,
    override val key: NamespacedKey,
    val reader: BinaryTagReader<T>,
    val writer: BinaryTagWriter<T>
) : BinaryTagType<T> {

    override fun read(input: DataInput): T = reader.read(input)

    override fun write(tag: T, output: DataOutput) = writer.write(tag, output)

    override fun hashCode(): Int = opcode

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as KarbonBinaryTagType<*>

        if (opcode != other.opcode) return false

        return true
    }

    class Factory : BinaryTagType.Factory {
        val byOpcode by lazy {
            val iterator = BinaryTagTypes.sequence().iterator()
            Array(13) {
                iterator.next()
            }
        }

        override fun of(opcode: Int): BinaryTagType<out BinaryTag> = byOpcode[opcode]
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun generate() = sequenceOf(
            create("end", 0, { EndBinaryTag }, { _, _ -> }),
            createNumeric(
                "byte",
                1,
                { ByteBinaryTag.of(it.readByte()) },
                { tag, output -> output.writeByte(tag.value.toInt()) }),
            createNumeric(
                "short",
                2,
                { ShortBinaryTag.of(it.readShort()) },
                { tag, output -> output.writeShort(tag.value.toInt()) }),
            createNumeric("int", 3, { IntBinaryTag.of(it.readInt()) }, { tag, output -> output.writeInt(tag.value) }),
            createNumeric(
                "long",
                4,
                { LongBinaryTag.of(it.readLong()) },
                { tag, output -> output.writeLong(tag.value) }),
            createNumeric(
                "float",
                5,
                { FloatBinaryTag.of(it.readFloat()) },
                { tag, output -> output.writeFloat(tag.value) }),
            createNumeric(
                "double",
                6,
                { DoubleBinaryTag.of(it.readDouble()) },
                { tag, output -> output.writeDouble(tag.value) }),
            create("byte_array", 7, { input ->
                ByteArrayBinaryTag.of(*ByteArray(input.readInt()) {
                    input.readByte()
                })
            }, { tag, output ->
                val value = tag.value
                output.writeInt(value.size)
                output.write(value)
            }),
            create("string", 8, { StringBinaryTag.of(it.readUTF()) }, { tag, output -> output.writeUTF(tag.value) }),
            create("list", 9, { input ->
                val type = BinaryTagType.of(input.readByte().toInt())
                val tags = List(input.readInt()) {
                    type.read(input)
                }
                ListBinaryTag(tags)
            }, { tag, output ->
                output.writeByte(tag.listType.opcode)
                output.writeInt(tag.size)
                tag.forEach {
                    (it.type as BinaryTagType<BinaryTag>).write(it, output)
                }
            }),
            create("compound", 10, { input ->
                val tags = HashMap<String, BinaryTag>()
                var type: BinaryTagType<out BinaryTag>
                while (true) {
                    type = BinaryTagType.of(input.readByte().toInt())
                    if (type == BinaryTagTypes.END) break
                    val key = input.readUTF()
                    val tag = type.read(input)
                    tags[key] = tag
                }
                CompoundBinaryTag(tags)
            }, { tag, output ->
                tag.forEach { entry ->
                    val value = entry.value
                    val type = value.type as BinaryTagType<BinaryTag>
                    output.writeByte(type.opcode)
                    if (type != BinaryTagTypes.END) {
                        output.writeUTF(entry.key)
                        type.write(value, output)
                    }
                }
                output.writeByte(BinaryTagTypes.END.opcode)
            }),
            create("int_array", 11, { input ->
                IntArrayBinaryTag.of(*IntArray(input.readInt()) {
                    input.readInt()
                })
            }, { tag, output ->
                val value = tag.value
                output.writeInt(value.size)
                value.forEach {
                    output.writeInt(it)
                }
            }),
            create("long_array", 12, { input ->
                LongArrayBinaryTag.of(*LongArray(input.readInt()) {
                    input.readLong()
                })
            }, { tag, output ->
                val value = tag.value
                output.writeInt(value.size)
                value.forEach {
                    output.writeLong(it)
                }
            })
        )

        private fun <T : NumberBinaryTag> createNumeric(
            key: String,
            opcode: Int,
            reader: BinaryTagReader<T>,
            writer: BinaryTagWriter<T>
        ) =
            key.toUpperCase() to {
                KarbonBinaryTagType<T>(
                    opcode,
                    true,
                    NamespacedKey.minecraft(key.toLowerCase()),
                    reader,
                    writer
                )
            }

        private fun <T : BinaryTag> create(
            key: String,
            opcode: Int,
            reader: BinaryTagReader<T>,
            writer: BinaryTagWriter<T>
        ) =
            key.toUpperCase() to {
                KarbonBinaryTagType<T>(
                    opcode,
                    false,
                    NamespacedKey.minecraft(key.toLowerCase()),
                    reader,
                    writer
                )
            }
    }
}