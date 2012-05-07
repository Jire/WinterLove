package com.winterlove.network.packet.events;

import com.winterlove.network.packet.Packet;
import com.winterlove.network.packet.PacketBuildEvent;

public class HandshakeBuildEvent extends PacketBuildEvent {

	public HandshakeBuildEvent(Packet packet) {
		super(packet);
	}

}