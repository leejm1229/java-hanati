package com.chapter12;

public class Hero {
	private String name;
	private int hp = 100;
	
	public Hero() {
		System.out.println("Hero 생성자");
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
