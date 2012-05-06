package com.winterlove.network.packet;


public class Packet {

	private final int id, length;
	private final byte[] data;
	private final boolean headless;

	public Packet(byte[] data) {
		this(-1, data.length, data, true);
	}

	public Packet(int id, byte[] data) {
		this(id, data.length, data);
	}

	public Packet(int id, int length, byte[] data) {
		this(id, length, data, false);
	}

	private Packet(int id, int length, byte[] data, boolean headless) {
		this.id = id;
		this.length = length;
		this.data = data;
		this.headless = headless;
	}

	public int getId() {
		return id;
	}

	public int getLength() {
		return length;
	}

	public byte[] getData() {
		return data;
	}

	public boolean isHeadless() {
		return headless;
	}

	@Override
	public String toString() {
		return "(id=" + id + ", length=" + length + ")";
	}

}