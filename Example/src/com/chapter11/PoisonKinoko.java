package com.chapter11;

public class PoisonKinoko extends Kinoko {

	private int count = 5;

	public PoisonKinoko(char suffix) {
		super(suffix);

	}

	@Override
	public void attack(Hero hero) {
		super.attack(hero);

		if (this.count > 0) {
			System.out.println("독 포자를 살포했다!");
			int damage = hero.getHp() / 5;
			hero.setHp(hero.getHp() - damage);
			System.out.println(damage + "포인트의 데미지");
			this.count -= 1;
		} else {
			System.out.println("남은 횟수는 0입니다.");
		}
	}
}
