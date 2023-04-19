package com.chapter13;

import java.util.Objects;

public class Hero {
	private String name;
	private int hp = 100;

//	@Override
//	public String toString() {
//		return "Hero [name=" + name + ", hp=" + hp + "]";
//	}
//
//	
	@Override
	public int hashCode() {
		int result = 37;
		result = result * 31 + this.getName().hashCode();
		result = result * 31 + this.getHp();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Hero))
			return false;
		Hero hero = (Hero) obj;
		if (!(this.name.equals(hero.name))) {
			return false;
		}
		return true;
	}

	public Hero() {
	
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

	// 싸우기
	public void attack(Kinoko enemy) {
		System.out.println(this.name + "의 공격!");
		this.hp -= 5;
		System.out.println("5포인트의 데미지를 주었다.");
	}

	// 도망
	public void run() {
		System.out.println(this.name + "는 도망쳤다!");
	}

}
