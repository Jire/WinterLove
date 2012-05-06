package com.winterlove.model.map;


public class Position {

	private int x, y, z;

	protected Position(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	protected Position(int x, int y) {
		this(x, y, 0);
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
		// FIXME (fidgy, but works...)
		return ((x & 0xFFF) << 20) | ((y & 0xFFF) << 8) | (z & 0xFF);
	}

	public static Position create(int hashCode) {
		int x = (hashCode >> 20) & 0xFFF;
		int y = (hashCode >> 8) & 0xFFF;
		int z = hashCode & 0xFF;
		return create(x, y, z);
	}

	public static Position create(int x, int y, int z) {
		return new Position(x, y, z);
	}

	public static Position create(int x, int y) {
		return new Position(x, y);
	}

}