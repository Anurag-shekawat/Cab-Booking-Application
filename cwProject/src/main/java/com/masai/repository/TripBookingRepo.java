package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.module.TripBooking;

@Repository
public interface TripBookingRepo extends JpaRepository<TripBooking, Integer> {

}
