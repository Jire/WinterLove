package com.winterlove.network.packet;

public abstract class PacketBuildEvent implements PacketEvent {

	private final Packet packet;

	protected PacketBuildEvent(Packet packet) {
		this.packet = packet;
	}

	public Packet getPacket() {
		return packet;
	}

}