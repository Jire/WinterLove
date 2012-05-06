package com.winterlove.model;

import java.util.ArrayList;
import java.util.List;

import com.winterlove.model.update.PlayerUpdateService;

public class World {

	private static final int MAXIMUM_PLAYERS = 2000;

	private final List<Player> players = new ArrayList<Player>(MAXIMUM_PLAYERS);
	private final PlayerUpdateService playerUpdateService = new PlayerUpdateService(MAXIMUM_PLAYERS);

	public boolean registerPlayer(Player player) {
		return players.add(player);
	}

	public boolean unregisterPlayer(Player player) {
		return players.remove(player);
	}

	public void updatePlayers() {
		playerUpdateService.update(players);
	}

}