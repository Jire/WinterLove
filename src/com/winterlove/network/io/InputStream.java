package com.winterlove.network.io;

import java.io.ByteArrayInputStream;

import com.winterlove.network.packet.Packet;

public class InputStream extends ByteArrayInputStream {

	public InputStream(byte[] data) {
		super(data);
	}

	public int readByteA() {
		return read() - 128;
	}

	public int readByteC() {
		return -read();
	}

	public int readUnsignedByte() {
		return read() & 0xFF;
	}

	public int readShort() {
		return (read() << 8) + read();
	}

	public int readShortA() {
		int i = (readUnsignedByte() << 8) + (read() - 128 & 0xFF);
		if(i > 32767)
			i -= 0x10000;
		return i;
	}

	public int readLEShort() {
		int i = readUnsignedByte() + (readUnsignedByte() << 8);
		if(i > 32767)
			i -= 0x10000;
		return i;
	}

	public int readLEShortA() {
		int i = (read() - 128 & 0xFF) + (readUnsignedByte() << 8);
		if(i > 32767)
			i -= 0x10000;
		return i;
	}

	public int readUnsignedShort() {
		return (readUnsignedByte() << 8) + readUnsignedByte();
	}

	public int readInteger() {
		return (readShort() << 16) + readShort();
	}

	public long readUnsignedInteger() {
		return (readUnsignedShort() << 16) + readUnsignedShort();
	}

	public long readLong() {
		return (readInteger() << 24) + readInteger();
	}

	public String readString() {
		StringBuilder buffer = new StringBuilder();
		int read;
		while ((read = readUnsignedByte()) != '\n' && super.available() != 0) {
			buffer.append((char) read);
		}
		return buffer.toString();
	}

	public static InputStream forPacket(Packet packet) {
		return new InputStream(packet.getData());
	}

}