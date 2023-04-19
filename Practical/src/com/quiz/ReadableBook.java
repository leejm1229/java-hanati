package com.quiz;

import java.util.List;

public class ReadableBook {
	private List<TreeCoordinate> trees;
	private ConstructionSiteCoordinate constructionSite;
	String[] state = { "silent", "noisy" };

	public ReadableBook(List<TreeCoordinate> trees, ConstructionSiteCoordinate constructionSite) {
		this.trees = trees;
		this.constructionSite = constructionSite;
	}

	public void noiseMeasurement() {
		for (TreeCoordinate tree : trees) {
			Coordinate point = tree.getCoordinate();
			if (constructionSite.getDistance(point) >= constructionSite.power(constructionSite.getR())) {
				System.out.println(state[0]);
			} else {
				System.out.println(state[1]);
			}
		}
	}
}
