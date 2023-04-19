package com.chapter11;

import com.chapter12.Character;
import com.chapter12.Kinoko;

public class Dancer extends Character {

	
	public Dancer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public void dance() {
		System.out.println(this.getName() + "은 정열적으로 춤을 췄다.");
	}

	@Override
	public void attack(Kinoko kinoko) {
		// TODO Auto-generated method stub
		
	}

}
