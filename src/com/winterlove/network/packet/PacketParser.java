package com.winterlove.network.packet;

public interface PacketParser {

	public PacketRepresentation parse(Packet packet);

}