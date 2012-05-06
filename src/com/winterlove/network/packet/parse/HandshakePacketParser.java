package com.winterlove.network.packet.parse;

import com.winterlove.network.io.InputStream;
import com.winterlove.network.packet.Packet;
import com.winterlove.network.packet.PacketParser;
import com.winterlove.network.packet.PacketRepresentation;
import com.winterlove.network.packet.ParsesPacket;
import com.winterlove.network.packet.reps.HandshakePacket;

@ParsesPacket(14)
public class HandshakePacketParser implements PacketParser {

	@Override
	public PacketRepresentation parse(Packet packet) {
		InputStream input = InputStream.forPacket(packet);

		int nameHash = input.readUnsignedByte();

		return HandshakePacket.get(nameHash);
	}

}