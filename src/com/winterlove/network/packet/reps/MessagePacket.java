package com.winterlove.network.packet.reps;

import com.winterlove.network.packet.PacketRepresentation;

public class MessagePacket implements PacketRepresentation {
	
	private final String message;
	
	protected MessagePacket(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public static MessagePacket forMessage(String message) {
		return new MessagePacket(message);
	}

}