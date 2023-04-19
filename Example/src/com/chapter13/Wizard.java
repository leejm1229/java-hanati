package com.chapter13;

public class Wizard extends Character {
	int mp;
	
	public Wizard() {
		
	}
	
	public Wizard(String name) {
		super(name);
	}

	@Override
	public void attack(Kinoko kinoko) {
		System.out.println(super.getName());
		System.out.println("적에게 3포인트의 데미지");
		kinoko.setHp(kinoko.getHp()-3);
	}
	
	public void fireball(Kinoko kinoko) {
		System.out.println(super.getName()+"는 불의 구슬을 맞았다.");
		System.out.println("적에게 20포인트의 데미지");
		kinoko.setHp(kinoko.getHp()-3);
		this.mp -= 5;
	}
}
