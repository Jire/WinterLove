package com.winterlove.network.io;

public class OutputStream {

	private static final int DEFAULT_SIZE = 64;

	private static final int BITMASKS[] = {
		0x0, 0x1, 0x3, 0x7,
		0xf, 0x1f, 0x3f, 0x7f,
		0xff, 0x1ff, 0x3ff, 0x7ff,
		0xfff, 0x1fff, 0x3fff, 0x7fff,
		0xffff, 0x1ffff, 0x3ffff, 0x7ffff,
		0xfffff, 0x1fffff, 0x3fffff, 0x7fffff,
		0xffffff, 0x1ffffff, 0x3ffffff, 0x7ffffff,
		0xfffffff, 0x1fffffff, 0x3fffffff, 0x7fffffff,
		-1
	};

	private byte[] data = new byte[DEFAULT_SIZE];
	private int position = 0;
	private int bitPosition = 0;

	private void unsafeWrite(int b) {
		data[position++] = (byte) b;
	}

	public void write(int b) {
		expand(1);
		unsafeWrite(b);
	}

	public void writeByteA(int b) {
		expand(1);
		unsafeWrite(b + 128);
	}

	public void writeByteC(int b) {
		expand(1);
		unsafeWrite(-b);
	}

	public void writeShort(int s) {
		expand(2);
		unsafeWrite(s >> 8);
		unsafeWrite(s);
	}

	public void writeShortA(int s) {
		expand(2);
		unsafeWrite(s >> 8);
		unsafeWrite(s + 128);
	}

	public void writeLEShort(int s) {
		expand(2);
		unsafeWrite(s);
		unsafeWrite(s >> 8);
	}

	public void writeLEShortA(int s) {
		expand(2);
		unsafeWrite(s + 128);
		unsafeWrite(s >> 8);
	}

	public void writeInteger(int i) {
		expand(4);
		unsafeWrite(i >> 24);
		unsafeWrite(i >> 16);
		unsafeWrite(i >> 8);
		unsafeWrite(i);
	}

	public void writeInteger1(int i) {
		expand(4);
		unsafeWrite(i >> 8);
		unsafeWrite(i);
		unsafeWrite(i >> 24);
		unsafeWrite(i >> 16);
	}

	public void writeLong(long l) {
		expand(8);
		unsafeWrite((int) (l >> 56));
		unsafeWrite((int) (l >> 48));
		unsafeWrite((int) (l >> 40));
		unsafeWrite((int) (l >> 32));
		unsafeWrite((int) (l >> 24));
		unsafeWrite((int) (l >> 16));
		unsafeWrite((int) (l >> 8));
		unsafeWrite((int) l);
	}

	public void writeString(String s) {
		expand(s.length() + 1);
		writeBytes(s.getBytes());
		unsafeWrite(10);
	}

	public void writeBytes(byte[] bs) {
		expand(bs.length);
		System.arraycopy(bs, 0, data, position, bs.length);
		position += bs.length;
	}

	public void writeBits(int numBits, int value) {
		int bytePos = bitPosition >> 3;
		int bitOffset = 8 - (bitPosition & 7);
		bitPosition += numBits;
		position = (bitPosition + 7) / 8;
		for (; numBits > bitOffset; bitOffset = 8) {
			expand(1);
			data[bytePos] &= ~ BITMASKS[bitOffset];	 // mask out the desired area
			data[bytePos++] |= value >> numBits - bitOffset & BITMASKS[bitOffset];

			numBits -= bitOffset;
		}
		if (numBits == bitOffset) {
			data[bytePos] &= ~ BITMASKS[bitOffset];
			data[bytePos] |= value & BITMASKS[bitOffset];
		} else {
			data[bytePos] &= ~ (BITMASKS[numBits] << bitOffset - numBits);
			data[bytePos] |= (value & BITMASKS[numBits]) << bitOffset - numBits;
		}
	}

	public int getLength() {
		return position;
	}

	public byte[] getData() {
		byte[] actualData = new byte[position];
		System.arraycopy(data, 0, actualData, 0, position);
		return actualData;
	}

	private void expand(int i) {
		if(position + i < data.length) {
			return;
		}
		byte[] newbuf = new byte[(position + i) * 2];
		System.arraycopy(data, 0, newbuf, 0, position);
		data = newbuf;
	}

}