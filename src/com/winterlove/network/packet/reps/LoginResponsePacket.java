package com.winterlove.network.packet.reps;

import com.winterlove.network.packet.PacketRepresentation;

public class LoginResponsePacket implements PacketRepresentation {

	private final int responseCode;
	private final int playerStatus;
	private final boolean flagged;

	protected LoginResponsePacket(int responseCode, int playerStatus, boolean flagged) {
		this.responseCode = responseCode;
		this.playerStatus = playerStatus;
		this.flagged = flagged;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public int getPlayerStatus() {
		return playerStatus;
	}

	public boolean isFlagged() {
		return flagged;
	}

	public static LoginResponsePacket get(int responseCode, int playerStatus, boolean flagged) {
		return new LoginResponsePacket(responseCode, playerStatus, flagged);
	}

}