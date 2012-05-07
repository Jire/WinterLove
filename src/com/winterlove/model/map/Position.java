package com.winterlove.model.map;

import java.util.HashMap;
import java.util.Map;

public class Position {

	private static final Map<Integer, Position> cache = new HashMap<>();

	private int x, y, z;

	protected Position(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public int getRegionX() {
		return (x >> 3) - 6;
	}

	public int getRegionY() {
		return (y >> 3) - 6;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setZ(int z) {
		this.z = z;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Position)) {
			return false;
		}
		Position pos = (Position) o;
		return getX() == pos.getX() && getY() == pos.getY() && getZ() == pos.getZ();
	}

	@Override
	public int hashCode() {
		return hashCode(getX(), getY(), getZ());
	}

	public static Position create(int hashCode) {
		int x = hashCode & 0x7FFF;
		int y = (hashCode >> 15) & 0x7FFF;
		int z = (hashCode >> 30) & 0b11;
		return create(x, y, z);
	}

	public static Position create(int x, int y, int z) {
		int hashCode = hashCode(x, y, z);
		if (cache.containsKey(hashCode)) {
			return cache.get(hashCode);
		}
		Position position = new Position(x, y, z);
		cache.put(hashCode, position);
		return position;
	}

	public static Position create(int x, int y) {
		return create(x, y, 0);
	}

	private static int hashCode(int x, int y, int z) {
		return ((z & 0b11) << 30) | ((y & 0x7FFF) << 15) | (x & 0x7FFF);
	}

}