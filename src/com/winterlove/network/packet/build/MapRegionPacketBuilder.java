package com.winterlove.network.packet.build;

import com.winterlove.network.io.OutputStream;
import com.winterlove.network.packet.BuildsPacket;
import com.winterlove.network.packet.Packet;
import com.winterlove.network.packet.PacketBuilder;
import com.winterlove.network.packet.PacketRepresentation;
import com.winterlove.network.packet.reps.MapRegionPacket;

@BuildsPacket(MapRegionPacket.class)
public class MapRegionPacketBuilder implements PacketBuilder {

	@Override
	public Packet build(PacketRepresentation packetRep) {
		MapRegionPacket packet = (MapRegionPacket) packetRep;

		OutputStream output = new OutputStream();
		output.writeShortA(packet.getPosition().getRegionX() + 6);
		output.writeShort(packet.getPosition().getRegionY() + 6);

		return new Packet(73, output.getData());
	}

}