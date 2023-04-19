package com.quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {

	private Scanner scanner;

	public Input() {
		scanner = new Scanner(System.in);
	}


	public List<TreeCoordinate> inputTrees() {
		int N = scanner.nextInt(); 
		List<TreeCoordinate> trees = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			trees.add(new TreeCoordinate(x, y, N));
		}
		return trees;
	}

	
	public ConstructionSiteCoordinate inputConstructionSite() {
		int x = scanner.nextInt();
		int y = scanner.nextInt();
		int R = scanner.nextInt();

		return new ConstructionSiteCoordinate(x, y, R);
	}
}
