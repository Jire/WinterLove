package com.winterlove.network.packet.events;

import com.winterlove.network.packet.PacketParseEvent;
import com.winterlove.network.packet.PacketRepresentation;

public class HandshakeParseEvent extends PacketParseEvent {

	public HandshakeParseEvent(PacketRepresentation packetRep) {
		super(packetRep);
	}

}