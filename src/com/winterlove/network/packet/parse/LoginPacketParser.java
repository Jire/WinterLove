package com.winterlove.network.packet.parse;

import com.winterlove.network.io.InputStream;
import com.winterlove.network.packet.Packet;
import com.winterlove.network.packet.PacketParser;
import com.winterlove.network.packet.PacketRepresentation;
import com.winterlove.network.packet.ParsesPacket;
import com.winterlove.network.packet.reps.LoginPacket;

@ParsesPacket({ 16, 18 })
public class LoginPacketParser implements PacketParser {

	@Override
	public PacketRepresentation parse(Packet packet) {
		InputStream input = InputStream.forPacket(packet);

		int magic = input.readUnsignedByte();
		int clientVersion = input.readUnsignedShort();
		boolean lowDetail = input.read() == 0;

		int[] fileChecksums = new int[9];
		for (int i = 0; i < fileChecksums.length; i++) {
			fileChecksums[i] = input.readInteger();
		}

		int blockLength = input.readUnsignedByte();
		int blockOperationCode = input.readUnsignedByte();

		int[] sessionKeys = new int[4];
		for (int i = 0; i < sessionKeys.length; i++) {
			sessionKeys[i] = input.readInteger();
		}

		int userId = input.readInteger();
		String username = input.readString();
		String password = input.readString();

		return LoginPacket.get(magic, clientVersion, lowDetail, fileChecksums
				, blockLength, blockOperationCode, sessionKeys, userId
				, username, password);
	}

}