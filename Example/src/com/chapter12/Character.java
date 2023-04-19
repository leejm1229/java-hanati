package com.chapter12;

public abstract class Character {
	private String name;
	private int hp;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public Character(String name) {
		this.name = name;
		hp = 100;
	}

	public void run() {
		System.out.println("도망쳤다.");
	}

	public abstract void attack(Kinoko kinoko);

}

