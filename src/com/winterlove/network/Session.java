package com.winterlove.network;

import java.net.Socket;

public class Session {

	private final Socket socket;

	public Session(Socket socket) {
		this.socket = socket;
	}

	public Socket getSocket() {
		return socket;
	}

}