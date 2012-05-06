package com.winterlove.network.packet.build;

import com.winterlove.network.io.OutputStream;
import com.winterlove.network.packet.BuildsPacket;
import com.winterlove.network.packet.Packet;
import com.winterlove.network.packet.PacketBuilder;
import com.winterlove.network.packet.PacketRepresentation;
import com.winterlove.network.packet.reps.LoginResponsePacket;

@BuildsPacket(LoginResponsePacket.class)
public class LoginResponseBuilder implements PacketBuilder {

	@Override
	public Packet build(PacketRepresentation packetRep) {
		LoginResponsePacket packet = (LoginResponsePacket) packetRep;

		OutputStream output = new OutputStream();
		output.write(packet.getResponseCode());
		output.write(packet.getPlayerStatus());
		output.write(packet.isFlagged() ? 1 : 0);

		return new Packet(output.getData());
	}

}