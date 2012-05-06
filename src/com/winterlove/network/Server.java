package com.winterlove.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

	private final int port;
	private volatile boolean running;

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
		setRunning(true);
		super.start();
	}

	@Override
	public void run() {
		try {
			final ServerSocket serverSocket = new ServerSocket(port);
			while (isRunning()) {
				Socket clientSocket = serverSocket.accept();
				clientSocket.setTcpNoDelay(true);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Server getServer(int port) {
		return new Server(port);
	}

}