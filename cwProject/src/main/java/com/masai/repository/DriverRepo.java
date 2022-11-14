package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.module.CabDriver;

@Repository
public interface DriverRepo extends JpaRepository<CabDriver, Integer> {

	public CabDriver findByMobile(String mobileNo);
	
}
