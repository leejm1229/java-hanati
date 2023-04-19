package com.chapter09;

import java.util.Random;

public class Cleric {

	private String name;
	private int hp;
	private int mp;
	public static final int MAX_HP = 50;
	public static final int MAX_MP = 10;

	Cleric(String name) {
		this(name, MAX_HP, MAX_MP);
	}

	Cleric(String name, int hp) {
		this(name, hp, MAX_MP);
	}

	Cleric(String name, int hp, int mp) {
		this.name = name;
		this.hp = hp;
		this.mp = mp;
	}

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

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public void selfAid() {
		if (this.mp < 5) {
			System.out.println("마나가 모자랍니다.");
		} else {
			this.mp -= 5;
			this.hp = MAX_HP;
		}
	}

	public int pray(int sec) {
		Random rand = new Random();
		int mpRecovery = rand.nextInt(3) + sec;

		if (mpRecovery > MAX_MP - this.mp) {
			mpRecovery = MAX_MP - this.mp;
		}
		this.mp += mpRecovery;

		return mpRecovery;
	}

}
