package com.winterlove.network;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.winterlove.network.packet.Packet;

public class Session {

	private final BlockingQueue<Packet> writePacketQueue = new ArrayBlockingQueue<>(256);
	private final Socket socket;

	public Session(Socket socket) {
		this.socket = socket;
	}

	public void write(Packet packet) throws IOException {
		socket.getOutputStream().write(packet.getData());
	}

	public Packet read() throws IOException {
		if (socket.getInputStream().available() < 1) {
			return null;
		}
		byte[] data = new byte[256];
		int packetId = socket.getInputStream().read();
		socket.getInputStream().read(data);
		return new Packet(packetId, data);
	}

	public void queuePacket(Packet packet) {
		writePacketQueue.add(packet);
	}

	public void processPackets() throws IOException {
		while (!writePacketQueue.isEmpty()) {
			write(writePacketQueue.poll());
		}
	}

}