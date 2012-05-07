package com.winterlove.network.packet;

import com.winterlove.event.EventListener;
import com.winterlove.event.HandlesEvent;
import com.winterlove.network.packet.events.HandshakeParseEvent;
import com.winterlove.network.packet.events.LoginBuildEvent;
import com.winterlove.network.packet.reps.HandshakePacket;

public class PacketEventListener implements EventListener {

	/*
	 * FIXME: testing
	 */
	
	@HandlesEvent
	public void handleHandshakeParse(HandshakeParseEvent event) {
		System.out.println(((HandshakePacket) event.getPacketRep()).getNameHash());
	}

	@HandlesEvent
	public void handleLoginBuild(LoginBuildEvent event) {
		System.out.println(event.getPacket().getId());
	}

}