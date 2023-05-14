package com.hanati.bonus.domain.repository;

import java.util.List;

import com.hanati.bonus.domain.model.Customer;
import com.hanati.bonus.domain.model.Emp;

public interface EmpRepository {
	// 보너스 계산 
	List<Emp> calCulatorEmp(List<Customer> customerList);
}
