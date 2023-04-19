package com.quiz;

public class TreeCoordinate extends Coordinate {
	private int N; 

	TreeCoordinate(int x, int y, int N) {
		super(x, y);
		this.setN(N);
	}

	public int getN() {
		return N;
	}

	public void setN(int n) {
		N = n;
	}
}
