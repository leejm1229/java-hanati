package com.quiz;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		
		Input input = new Input();
		ConstructionSiteCoordinate constructionSite = input.inputConstructionSite();
		List<TreeCoordinate> trees = input.inputTrees();
		ReadableBook readableBook = new  ReadableBook(trees, constructionSite);
		readableBook.noiseMeasurement();
		
	}

}
