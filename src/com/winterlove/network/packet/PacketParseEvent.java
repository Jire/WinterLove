package com.winterlove.network.packet;

public abstract class PacketParseEvent implements PacketEvent {

	private final PacketRepresentation packetRep;

	protected PacketParseEvent(PacketRepresentation packetRep) {
		this.packetRep = packetRep;
	}

	public PacketRepresentation getPacketRep() {
		return packetRep;
	}

}