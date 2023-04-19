package com.chapter12;

public abstract class TangibleAsset extends Asset implements Thing {

	private String color;
	private double weight;

	TangibleAsset(String name, int price, String color) {
		super(name, price);
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getWeight() {
		return this.weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
}
