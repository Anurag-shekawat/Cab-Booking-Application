package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.module.Cab;

@Repository
public interface CabRepo extends JpaRepository<Cab, Integer> {

	public List<Cab> findByCarType(String carType);
	
}
