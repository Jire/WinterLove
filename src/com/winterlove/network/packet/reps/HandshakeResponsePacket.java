package com.winterlove.network.packet.reps;

import com.winterlove.network.packet.PacketRepresentation;

public class HandshakeResponsePacket implements PacketRepresentation {

	private final int responseCode;
	private final long sessionKey;

	protected HandshakeResponsePacket(int responseCode, long sessionKey) {
		this.responseCode = responseCode;
		this.sessionKey = sessionKey;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public long getSessionKey() {
		return sessionKey;
	}

	public static HandshakeResponsePacket get(int responseCode, long sessionKey) {
		return new HandshakeResponsePacket(responseCode, sessionKey);
	}

}