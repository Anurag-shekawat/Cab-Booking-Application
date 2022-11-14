package com.masai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.module.TripBooking;
import com.masai.repository.TripBookingRepo;

@Service
public class TripBookingServiceImpl implements TripBookingService {

	@Autowired
	private TripBookingRepo tbRepo;
	
//	@Override
//	public TripBooking insertTripBooking(TripBooking tripBooking) {
//		
//	}

}
