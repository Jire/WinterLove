package com.winterlove.network.packet.build;

import com.winterlove.network.io.OutputStream;
import com.winterlove.network.packet.BuildsPacket;
import com.winterlove.network.packet.Packet;
import com.winterlove.network.packet.PacketBuilder;
import com.winterlove.network.packet.PacketRepresentation;
import com.winterlove.network.packet.reps.HandshakeResponsePacket;

@BuildsPacket(HandshakeResponsePacket.class)
public class HandshakeResponseBuilder implements PacketBuilder {

	private static final int[] INITIAL_RESPONSE = { 0, 0, 0, 0, 0, 0, 0, 0 };

	@Override
	public Packet build(PacketRepresentation packetRep) {
		HandshakeResponsePacket packet = (HandshakeResponsePacket) packetRep;

		OutputStream output = new OutputStream();
		for (int i : INITIAL_RESPONSE) {
			output.write(i);
		}
		output.write(packet.getResponseCode());
		output.writeLong(packet.getSessionKey());

		return new Packet(output.getData());
	}

}