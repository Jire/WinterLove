package com.winterlove.network.packet.build;

import com.winterlove.network.io.OutputStream;
import com.winterlove.network.packet.BuildsPacket;
import com.winterlove.network.packet.Packet;
import com.winterlove.network.packet.PacketBuilder;
import com.winterlove.network.packet.PacketRepresentation;
import com.winterlove.network.packet.reps.MessagePacket;

@BuildsPacket(MessagePacket.class)
public class MessagePacketBuilder implements PacketBuilder {

	@Override
	public Packet build(PacketRepresentation packet) {
		MessagePacket messagePacket = (MessagePacket) packet;
		OutputStream outputStream = new OutputStream();
		outputStream.writeString(messagePacket.getMessage());
		return new Packet(253, outputStream.getData());
	}

}