package com.chapter11;

public class SuperHero extends Hero {
	private boolean flying;
	
	public SuperHero() {
		System.out.println("SuperHero의 생성자");
	}

	// 추가한 메서드
	public void fly() {
		flying = true;
		System.out.println("날았다!");
	}

	// 추가한 메서드
	public void land() {
		flying = false;
		System.out.println("착지했다!");
	}

	@Override
	public void run() {
		System.out.println("퇴각했다.");
	}

	@Override
	public void attack(Kinoko enemy) {
		super.attack(enemy);

		if (this.flying) {
			System.out.println(this.getName() + "의 공격!");
			enemy.setHp(enemy.getHp() - 5);
			System.out.println("5포인트의 데미지를 주었다.");
		}
	}
}
