package com.masai.service;

import java.util.List;

import com.masai.exception.CustomerException;
import com.masai.module.Customer;

public interface CustomerService {

	public Customer createCustomer(Customer customer)throws CustomerException;
	public Customer updateCustomer(Customer customer,String key) throws CustomerException;
	public Customer deleteCustomer(Integer customerId) throws CustomerException;
	public List<Customer> viewCustomers() throws CustomerException;
	public Customer viewCustomer(Integer customerId) throws CustomerException;
	
}
