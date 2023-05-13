package com.hanati.bonus.domain.model;

import java.util.List;

public class Bonus {
	private List<Bonus> bonusList;
	private String empno;
	private String ename;
	private int bonus;

	public Bonus(String empno, String ename, int bonus) {
		this.empno = empno;
		this.ename = ename;
		this.bonus = bonus;
	}

	public List<Bonus> getBonusList() {
		return bonusList;
	}

	public void setBonusList(List<Bonus> bonusList) {
		this.bonusList = bonusList;
	}

	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

}
