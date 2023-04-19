package com.chapter09;

public class Main {

	public static void main(String[] args) {

		Cleric c1 = new Cleric("아서스", 40, 5);
		Cleric c2 = new Cleric("아서스", 35);
		Cleric c3 = new Cleric("아서스");

		System.out.println("HP : " + c1.getHp() + ", MP : " + c1.getMp());
		System.out.println("HP : " + c2.getHp() + ", MP : " + c2.getMp());
		System.out.println("HP : " + c3.getHp() + ", MP : " + c3.getMp());
	}

}
