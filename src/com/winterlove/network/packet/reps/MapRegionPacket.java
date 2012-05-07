package com.winterlove.network.packet.reps;

import com.winterlove.model.map.Position;
import com.winterlove.network.packet.PacketRepresentation;

public class MapRegionPacket implements PacketRepresentation {

	private final Position position;

	protected MapRegionPacket(Position position) {
		this.position = position;
	}

	public Position getPosition() {
		return position;
	}

	public static MapRegionPacket get(Position position) {
		return new MapRegionPacket(position);
	}

}