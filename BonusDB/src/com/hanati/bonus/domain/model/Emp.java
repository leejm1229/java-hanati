package com.hanati.bonus.domain.model;

import java.util.List;

public class Emp {
	private List<Emp> empList;
	private int empno;
	private String ename;
	private String job;
	private int sal;
	private int comm; // 기존 커미션 
	private int countCustomer; // 고객관리 인원 확인

	public Emp(int empno, String ename, String job, int sal, int comm, int countCustomer) {
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.sal = sal;
		this.comm = comm;
		this.countCustomer = countCustomer;
	}

	public List<Emp> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Emp> empList) {
		this.empList = empList;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	public int getComm() {
		return comm;
	}

	public void setComm(int comm) {
		this.comm = comm;
	}

	public int getCountCustomer() {
		return countCustomer;
	}

	public void setCountCustomer(int countCustomer) {
		this.countCustomer = countCustomer;
	}

}
