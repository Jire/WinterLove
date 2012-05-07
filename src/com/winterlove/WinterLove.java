package com.winterlove;

import com.winterlove.engine.Engine;
import com.winterlove.event.EventManager;
import com.winterlove.model.World;
import com.winterlove.network.Server;
import com.winterlove.network.packet.PacketEventListener;

public final class WinterLove {

	// making singleton k

	private static final int DEFAULT_CYCLE_LENGTH = 600;
	private static final int DEFAULT_PORT = 43594;

	private static final WinterLove winterlove = new WinterLove(
			Engine.getEngine(DEFAULT_CYCLE_LENGTH),
			Server.getServer(DEFAULT_PORT),
			new World(),
			new EventManager());

	private final Engine engine;
	private final Server server;
	private final World world;
	private final EventManager eventManager;

	private WinterLove(Engine engine, Server server, World world, EventManager eventManager) {
		this.engine = engine;
		this.server = server;
		this.world = world;
		this.eventManager = eventManager;
	}

	private void start() {
		engine.start();
		server.start();

		// register events
		eventManager.registerListener(new PacketEventListener());

		engine.submit(world);
	}

	public static void main(String... args) {
		winterlove.start();

		System.out.println("WinterLove is ready.");
	}

}