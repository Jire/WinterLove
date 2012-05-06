package com.winterlove.network.packet;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

public class PacketService {

	private final Map<Class<? extends PacketRepresentation>, PacketBuilder> builders = new HashMap<>(256);
	private final Map<Integer, PacketParser> parsers = new HashMap<>(256);

	public boolean registerBuilder(Class<?> builder) {
		Object object = null;
		try {
			object = builder.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		if (!(object instanceof PacketBuilder)) {
			return false;
		}
		BuildsPacket annotation = object.getClass().getAnnotation(BuildsPacket.class);
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

	public boolean registerParser(Class<?> parser) {
		Object object = null;
		try {
			object = parser.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		if (!(object instanceof PacketBuilder)) {
			return false;
		}
		ParsesPacket annotation = object.getClass().getAnnotation(ParsesPacket.class);
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

	public Packet buildPacket(PacketRepresentation packet) {
		PacketBuilder builder = builders.get(packet);
		if (builder != null) {
			return builder.build(packet);
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

}