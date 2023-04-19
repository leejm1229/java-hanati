package com.chapter10;

public class Wand {
	private String name;
	private double power;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null || name.length() < 3) {
			throw new IllegalArgumentException("이름은 null이 아니며 3글자 이상이어야 합니다.");
		} else {
			this.name = name;
		}
	}

	public double getPower() {
		return power;
	}

	public void setPower(double power) {
		if (power < 0.5 || power > 100) {
			throw new IllegalArgumentException("지팡이의 마력은 0.5 이상 100.0 이하여야 한다.");
		}else {
			this.power = power;
		}
	}
}
