package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.AdminException;
import com.masai.module.Admin;
import com.masai.module.Cab;
import com.masai.module.CurrentUserSession;
import com.masai.module.TripBooking;
import com.masai.repository.AdminRepo;
import com.masai.repository.CabRepo;
import com.masai.repository.SessionDAO;
import com.masai.repository.TripBookingRepo;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private CabRepo cabDao;
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private SessionDAO sDao;
	
	@Autowired
	private TripBookingRepo tbRepo;
	
	@Override
	public Admin insertAdmin(Admin admin) throws AdminException {
		Admin existingAdmin = adminRepo.findByMobile(admin.getMobile());
		if (existingAdmin != null) {
			throw new AdminException("Admin already registerd with the mobile number");
		}

		return adminRepo.save(admin);
	}

	@Override
	public Admin updateAdmin(Admin admin, String key) throws AdminException {
		CurrentUserSession loggedInUser = sDao.findByUuid(key);
		if (loggedInUser == null) {
			throw new AdminException("Please provide a valid key to update the Admin...");
		}

		// if logged in userId is same as the id of supplied customer which we want to
		// update
		if (admin.getAdminId() == loggedInUser.getUserId()) {
			return adminRepo.save(admin);
		} else {
			throw new AdminException("Invalid Admin details, please login first");
		}
	}

	@Override
	public Admin deleteAdmin(Integer adminId, String key) throws AdminException {
		CurrentUserSession loggedInUser = sDao.findByUuid(key);
		if (loggedInUser == null) {
			throw new AdminException("please enter a valid key");
		}

		if (adminId==loggedInUser.getUserId()) {
			Optional<Admin> admin = adminRepo.findById(adminId);
			if (admin.isPresent()) {
				adminRepo.delete(admin.get());
				sDao.delete(loggedInUser);
				return admin.get();
			} else {
				throw new AdminException("No Admin exist with customer id: " + adminId);
			}
		}else {
			throw new AdminException("Invalid admin details");
		}
	}
	
	@Override
	public List<TripBooking> getAllTripsByCab(Integer cabId, Integer adminId, String key) throws AdminException {

		CurrentUserSession loggedInUser = sDao.findByUuid(key);

		if (loggedInUser == null) {
			throw new AdminException("Please provide a valid key");
		}

		if (adminId == loggedInUser.getUserId()) {

			Cab cab = cabDao.findById(cabId).get();
			if (cab == null)
				throw new AdminException("Cab Does not Exist");
			List<TripBooking> allTripsBycab = cab.getCabDriver().getTripDetailsList();

			return allTripsBycab;
		} else {
			throw new AdminException("wrong Details please login first!");
		}

	}
	
	public List<TripBooking> getAllTrips(Integer adminId, String key) throws AdminException {
		// TODO Auto-generated method stub

		CurrentUserSession loggedInUser = sDao.findByUuid(key);

		if (loggedInUser == null) {
			throw new AdminException("Please provide a valid key to get all the trips");
		}

		if (adminId == loggedInUser.getUserId()) {

			List<TripBooking> alltrip = tbRepo.findAll();
			if (alltrip.size() > 0) {
				return alltrip;
			}
			throw new AdminException("No trip found");
		} else {
			throw new AdminException("Wrong details please login first!");
		}

	}

}
