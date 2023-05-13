package com.hanati.bonus.domain.model;

import java.util.List;

// 단순 보너스 계산만을 위해 필요한 컬럼만 추출
public class Customer {
	private List<Customer> customerList;
	private int mgr_empno;
	private int count; // 각 매니저가 몇명의 고객을 관리하는지 count

	public Customer(int mgr_empno, int count) {
		this.mgr_empno = mgr_empno;
		this.count = count;
	}

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	public Customer(int mgr_empno) {
		this.mgr_empno = mgr_empno;
	}

	public int getMgr_empno() {
		return mgr_empno;
	}

	public void setMgr_empno(int mgr_empno) {
		this.mgr_empno = mgr_empno;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
