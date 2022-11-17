package com.masai.service;

import java.util.List;

import com.masai.exception.TripBookingException;
import com.masai.module.TripBooking;
import com.masai.module.TripBookingDTO;

public interface TripBookingService {

	public TripBooking insertTripBooking(TripBookingDTO tripBooking, String key) throws TripBookingException;

//	public TripBooking updateTripBooking(TripBooking tripBooking);
	
	public String deleteTripBooking(Integer customerId, String key) throws TripBookingException;

	public List<TripBooking> viewAllTripsCustomer(int customerId, String key) throws TripBookingException;
	
	public String calculateBill(Integer driverId, String key) throws TripBookingException;

}
