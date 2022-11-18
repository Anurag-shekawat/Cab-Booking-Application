package com.masai.service;

import java.util.List;

import com.masai.exception.AdminException;
import com.masai.module.Admin;
import com.masai.module.TripBooking;

public interface AdminService {

	public Admin insertAdmin(Admin admin) throws AdminException;

	public Admin updateAdmin(Admin admin, String key) throws AdminException;

	public Admin deleteAdmin(Integer adminId, String key) throws AdminException;
	
	public List<TripBooking> getAllTripsByCab(Integer cabId, Integer adminId, String key) throws AdminException;
	
	public List<TripBooking> getAllTrips(Integer adminId, String key) throws AdminException;
	
//	public List<TripBooking> getAllTrips(int customerId);
//	public List<TripBooking> getTripsCustomerWise();
//	public List<TripBooking> getTripsDateWise();
//	public List<TripBooking> getAllTripsForDays(Integer customerId, LocalDateTime fromDate, LocalDateTime toDate);

}
