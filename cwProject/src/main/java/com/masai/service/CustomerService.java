package com.masai.service;

import com.masai.exception.CustomerException;
import com.masai.module.Customer;

public interface CustomerService {

	public Customer createCustomer(Customer customer)throws CustomerException;
	public Customer updateCusomer(Customer customer,String key) throws CustomerException;
	
}
