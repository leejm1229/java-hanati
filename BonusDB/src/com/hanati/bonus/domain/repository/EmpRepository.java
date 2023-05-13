package com.hanati.bonus.domain.repository;

import java.util.List;

import com.hanati.bonus.domain.model.Customer;
import com.hanati.bonus.domain.model.Emp;

public interface EmpRepository {
	List<Emp> calCulatorEmp(List<Customer> customerList);
}
