package com.winterlove;

import com.winterlove.engine.Engine;
import com.winterlove.network.Server;

public final class WinterLove {

	private static final int DEFAULT_CYCLE_LENGTH = 600;
	private static final int DEFAULT_PORT = 43594;

	private final Engine engine;
	private final Server server;

	private WinterLove(Engine engine, Server server) {
		this.engine = engine;
		this.server = server;
	}

	private void start() {
		engine.start();
		server.start();
	}

	public static void main(String... args) {
		int cycleLength = DEFAULT_CYCLE_LENGTH;
		int port = DEFAULT_PORT;

		if (args.length >= 1) {
			cycleLength = Integer.parseInt(args[0]);
		}
		if (args.length >= 2) {
			port = Integer.parseInt(args[1]);
		}

		new WinterLove(Engine.getEngine(cycleLength), Server.getServer(port)).start();
	}

}