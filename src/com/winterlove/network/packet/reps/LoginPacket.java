package com.winterlove.network.packet.reps;

import com.winterlove.network.packet.PacketRepresentation;

public class LoginPacket implements PacketRepresentation {

	private final int magic;
	private final int clientVersion;
	private final boolean lowDetail;
	private final int[] fileChecksums;
	private final int blockLength;
	private final int blockOperationCode;
	private final int[] sessionKeys;
	private final int userId;
	private final String username;
	private final String password;

	protected LoginPacket(int magic, int clientVersion, boolean lowDetail, int[] fileChecksums
			, int blockLength, int blockOperationCode, int[] sessionKeys, int userId
			, String username, String password) {
		this.magic = magic;
		this.clientVersion = clientVersion;
		this.lowDetail = lowDetail;
		this.fileChecksums = fileChecksums;
		this.blockLength = blockLength;
		this.blockOperationCode = blockOperationCode;
		this.sessionKeys = sessionKeys;
		this.userId = userId;
		this.username = username;
		this.password = password;
	}

	public int getMagic() {
		return magic;
	}

	public int getClientVersion() {
		return clientVersion;
	}

	public boolean isLowDetail() {
		return lowDetail;
	}

	public int[] getFileChecksums() {
		return fileChecksums;
	}

	public int getBlockLength() {
		return blockLength;
	}

	public int getBlockOperationCode() {
		return blockOperationCode;
	}

	public int[] getSessionKeys() {
		return sessionKeys;
	}

	public int getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public static LoginPacket get(int magic, int clientVersion, boolean lowDetail, int[] fileChecksums
			, int blockLength, int blockOperationCode, int[] sessionKeys, int userId
			, String username, String password) {
		return new LoginPacket(magic, clientVersion, lowDetail, fileChecksums
				, blockLength, blockOperationCode, sessionKeys, userId
				, username, password);
	}

}