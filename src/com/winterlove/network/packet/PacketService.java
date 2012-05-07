package com.winterlove.network.packet;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import com.winterlove.network.Client;

public class PacketService {

	private final Map<Class<? extends PacketRepresentation>, PacketBuilder> builders = new HashMap<>(256);
	private final Map<Integer, PacketParser> parsers = new HashMap<>(256);

	public boolean registerBuilder(Class<? extends PacketBuilder> builder) {
		Object object = null;
		try {
			object = builder.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		BuildsPacket annotation = builder.getAnnotation(BuildsPacket.class);
		if (annotation == null) {
			return false;
		}
		builders.put(annotation.value(), (PacketBuilder) object);
		return true;
	}

	public void registerBuilders(String packageName) {
		Reflections reflections = new Reflections(packageName);
		Set<Class<? extends PacketBuilder>> builders = reflections.getSubTypesOf(PacketBuilder.class);
		for (Class<? extends PacketBuilder> builder : builders) {
			registerBuilder(builder);
		}
	}

	public boolean registerParser(Class<? extends PacketParser> parser) {
		Object object = null;
		try {
			object = parser.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		ParsesPacket annotation = parser.getAnnotation(ParsesPacket.class);
		if (annotation == null) {
			return false;
		}
		for (int packetId : annotation.value()) {
			parsers.put(packetId, (PacketParser) object);
		}
		return true;
	}

	public void registerParsers(String packageName) {
		Reflections reflections = new Reflections(packageName);
		Set<Class<? extends PacketParser>> parsers = reflections.getSubTypesOf(PacketParser.class);
		for (Class<? extends PacketParser> parser : parsers) {
			registerParser(parser);
		}
	}

	public Packet buildPacket(PacketRepresentation packetRep) {
		PacketBuilder builder = builders.get(packetRep);
		if (builder != null) {
			Packet packet = builder.build(packetRep);
			return packet;
		}
		return null;
	}

	public PacketRepresentation parsePacket(int packetId, Packet packet) {
		PacketParser parser = parsers.get(packetId);
		if (parser != null) {
			return parser.parse(packet);
		}
		return null;
	}

	public void buildPacketAndShip(PacketRepresentation packet, Client client) {
		client.getSession().queuePacket(buildPacket(packet));
	}

}