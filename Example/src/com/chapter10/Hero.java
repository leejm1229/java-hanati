package com.chapter10;

public class Hero {

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

	private void die() {
		System.out.println(this.name + "는 죽었다.");
		System.out.println("Game Over");
	}

	public void attack() {
		System.out.println(this.name + "의 공격!");
		this.hp -= 2;
		if (this.hp <= 0) {
			this.die();
		}
	}

	void run() {
		System.out.println(this.name + "는 도망쳤다.");
		System.out.println("최종 HP는 " + this.hp + "입니다.");
	}

	void sit(int sec) {
		this.hp += sec;
		System.out.println(this.name + "는 " + sec + "초 앉았다.");
		System.out.println("HP가 " + sec + "포인트 회복되었다.");
	}

	void slip() {
		this.hp -= 5;
		System.out.println(this.name + "는 넘어졌다.");
		System.out.println("5의데미");
	}

	void sleep() {
		this.hp = 100;
		System.out.println(this.name + "는 잠을 자고 회복했다.");
	}

}
