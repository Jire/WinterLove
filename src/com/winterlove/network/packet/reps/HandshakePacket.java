package com.winterlove.network.packet.reps;

import com.winterlove.network.packet.PacketRepresentation;

public class HandshakePacket implements PacketRepresentation {

	private final int nameHash;

	protected HandshakePacket(int nameHash) {
		this.nameHash = nameHash;
	}

	public int getNameHash() {
		return nameHash;
	}

	public static HandshakePacket get(int nameHash) {
		return new HandshakePacket(nameHash);
	}

}