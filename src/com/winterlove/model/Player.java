package com.winterlove.model;

import java.io.IOException;

import com.winterlove.network.Client;
import com.winterlove.network.Session;

public class Player extends Entity implements Client {

	private final Session session;

	public Player(Session session) {
		this.session = session;
	}

	@Override
	public Session getSession() {
		return session;
	}

	@Override
	public void process() {
		try {
			getSession().processPackets();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}