package com.winterlove.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.winterlove.network.packet.PacketService;
import com.winterlove.network.packet.build.HandshakeResponseBuilder;
import com.winterlove.network.packet.build.LoginResponseBuilder;
import com.winterlove.network.packet.parse.HandshakePacketParser;
import com.winterlove.network.packet.parse.LoginPacketParser;

public class Server extends Thread {

	private final int port;
	private volatile boolean running;

	private final PacketService packetService = new PacketService();

	protected Server(int port) {
		super("Server");
		this.port = port;
	}

	public int getPort() {
		return port;
	}

	public boolean isRunning() {
		return running;
	}

	protected void setRunning(boolean running) {
		this.running = running;
	}

	@Override
	public void start() {
		if (isRunning()) {
			throw new IllegalStateException("Cannot start server while already running");
		}

		setRunning(true);

		packetService.registerBuilder(HandshakeResponseBuilder.class);
		packetService.registerBuilder(LoginResponseBuilder.class);
		packetService.registerParser(HandshakePacketParser.class);
		packetService.registerParser(LoginPacketParser.class);

		super.start();
	}

	@Override
	public void run() {
		try {
			final ServerSocket serverSocket = new ServerSocket(port);
			while (isRunning()) {
				final Socket clientSocket = serverSocket.accept();
				clientSocket.setTcpNoDelay(true);
				new Thread() {
					@Override
					public void run() {
						try {
							/*
							 * _dummy_ code
							 * XXX
							 */
							@SuppressWarnings("unused")
							int packetId = -1;
							while ((packetId = clientSocket.getInputStream().read()) >= 0) {
								byte[] payload = new byte[256];
								clientSocket.getInputStream().read(payload);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Server getServer(int port) {
		return new Server(port);
	}

}