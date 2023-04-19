package com.quiz;

public class Coordinate {
	private int x; // x 좌표
	private int y; // y 좌표

	Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Coordinate getCoordinate() {
		return this;
	}

	public double power(int R) {
		return Math.pow(R, 2);
	}

	public double getDistance(Coordinate coordinate) { 
		int X = this.x - coordinate.x;
		int Y = this.y - coordinate.y;

		return power(X) + power(Y);
	}
}
