package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.module.Customer;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, Integer> {

	public Customer findByMobile(String mobileNo);
	
}