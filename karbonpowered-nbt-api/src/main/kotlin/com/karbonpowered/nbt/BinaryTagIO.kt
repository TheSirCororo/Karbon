package com.karbonpowered.nbt

import com.karbonpowered.network.message.MessageBuf
import java.io.*

fun DataOutput.writeNbt(tag: CompoundBinaryTag) {
    writeByte(BinaryTagTypes.COMPOUND.opcode)
    writeUTF("") // write empty name
    BinaryTagTypes.COMPOUND.write(tag, this)
}

fun DataInput.readNbt(): CompoundBinaryTag {
    val opcode = readByte().toInt()
    val type = BinaryTagType.of(opcode)
    if (type != BinaryTagTypes.COMPOUND) {
        throw IOException("Expected root tag to be a ${BinaryTagTypes.COMPOUND}, but was $type")
    }
    skipBytes(readUnsignedShort()) // read empty name
    return BinaryTagTypes.COMPOUND.read(this)
}

fun MessageBuf.writeNbt(tag: CompoundBinaryTag): MessageBuf = apply {
    DataOutputStream(asOutputStream()).writeNbt(tag)
}

fun MessageBuf.readNbt(): CompoundBinaryTag =
    DataInputStream(asInputStream()).readNbt()