package com.winterlove.model;

import java.util.ArrayList;
import java.util.List;

import com.winterlove.engine.Task;

public class World implements Task {

	private static final int MAXIMUM_PLAYERS = 2000;

	private final List<Player> players = new ArrayList<Player>(MAXIMUM_PLAYERS);

	public boolean registerPlayer(Player player) {
		return players.add(player);
	}

	public boolean unregisterPlayer(Player player) {
		return players.remove(player);
	}

	@Override
	public boolean run() {
		try {
			for (Player player : players) {
				player.process();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

}