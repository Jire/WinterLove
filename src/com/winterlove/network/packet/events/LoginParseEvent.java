package com.winterlove.network.packet.events;

import com.winterlove.network.packet.PacketParseEvent;
import com.winterlove.network.packet.PacketRepresentation;

public class LoginParseEvent extends PacketParseEvent {

	public LoginParseEvent(PacketRepresentation packetRep) {
		super(packetRep);
	}

}