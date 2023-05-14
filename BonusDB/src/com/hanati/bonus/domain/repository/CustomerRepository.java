package com.hanati.bonus.domain.repository;

import java.util.List;

import com.hanati.bonus.domain.model.Customer;

public interface CustomerRepository {
	
	// CUSTOMER 테이블에서 각 customer의 mgr_empno를 가지고 온다.
	// 그리고 카운트 
	List<Customer> loadCustomer();
}
