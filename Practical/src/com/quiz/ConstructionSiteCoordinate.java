package com.quiz;

public class ConstructionSiteCoordinate extends Coordinate {
	private int R;

	ConstructionSiteCoordinate(int x, int y, int R) {
		super(x, y);
		this.setR(R);
	}

	public int getR() {
		return R;
	}

	public void setR(int r) {
		R = r;
	}

}
