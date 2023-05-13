package com.hanati.bonus.domain.repository;

import java.util.List;

import com.hanati.bonus.domain.model.Customer;

public interface CustomerRepository {
	
	List<Customer> loadCustomer();
}
