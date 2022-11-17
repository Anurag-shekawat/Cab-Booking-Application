package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.TripBookingException;
import com.masai.module.CabDriver;
import com.masai.module.CurrentUserSession;
import com.masai.module.Customer;
import com.masai.module.TripBooking;
import com.masai.module.TripBookingDTO;
import com.masai.repository.CustomerDAO;
import com.masai.repository.DriverRepo;
import com.masai.repository.SessionDAO;
import com.masai.repository.TripBookingRepo;

@Service
public class TripBookingServiceImpl implements TripBookingService {

	@Autowired
	private SessionDAO sDao;

	@Autowired
	private TripBookingRepo tdao;

	@Autowired
	private DriverRepo ddao;

	@Autowired
	private CustomerDAO cdao;

	@Override
	public TripBooking insertTripBooking(TripBookingDTO tripBooking, String key) throws TripBookingException {
		CurrentUserSession loggedInUser = sDao.findByUuid(key);
		if (loggedInUser == null) {
			throw new TripBookingException("Please provide a valid key");
		}

		if (tripBooking.getCustomerId() == loggedInUser.getUserId()) {
			Optional<Customer> customer = cdao.findById(tripBooking.getCustomerId());

			if (customer.isPresent()) {
				Customer c = customer.get();
				TripBooking tripB = new TripBooking();

				tripB.setFromLocation(tripBooking.getFromLocation());
				tripB.setToLocation(tripBooking.getToLocation());
				tripB.setFromDateTime(tripBooking.getFromTime());
				tripB.setToDateTime(tripBooking.getToTime());
				int min = 10;
				int max = 100;
				float distance = (float) Math.floor(Math.random() * (max - min + 1) + min);
				tripB.setDistance(distance);

				tripB.setCustomer(c);
				List<CabDriver> driverlist = ddao.findAll();

				CabDriver driver = null;
				for (int i = 0; i < driverlist.size(); i++) {
					if (driverlist.get(i).getAvailablity() == true) {
						driver = driverlist.get(i);
						break;
					}
				}

				if (driver == null)
					throw new TripBookingException("No Driver Available at the moment");

				tripB.setCabDriver(driver);
				driver.getTripDetailsList().add(tripB);
				driver.setAvailablity(false);

				c.getTripDetailsList().add(tripB);

				tdao.save(tripB);

				return tripB;

			} else {
				throw new TripBookingException("Customer not found with id " + tripBooking.getCustomerId());
			}

		} else {
			throw new TripBookingException("Wrong details Please login first");
		}
	}

	@Override
	public String deleteTripBooking(Integer customerId, String key) throws TripBookingException {
		CurrentUserSession loggedInUser = sDao.findByUuid(key);
		if (loggedInUser == null) {
			throw new TripBookingException("Please provide a valid key to delete the Trip");
		}

		if (customerId == loggedInUser.getUserId()) {

			Optional<Customer> customer = cdao.findById(customerId);
			if (customer.isPresent()) {
				Customer cus = customer.get();
				List<TripBooking> tripB = cus.getTripDetailsList();

				if (tripB.size() > 0) {
					if (tripB.get(tripB.size() - 1).getStatus() == false) {
						CabDriver driver = tripB.get(tripB.size() - 1).getCabDriver();
						driver.setAvailablity(true);
						ddao.save(driver);
						tripB.remove(tripB.size() - 1);
						cdao.save(cus);

						return "Trip cancelled Successfully";
					}
				}
				return "No Trip found";

			} else {
				throw new TripBookingException("Customer not found with id :" + customerId);
			}
		} else {
			throw new TripBookingException("Wrong Details Please login first");
		}

	}

	@Override
	public List<TripBooking> viewAllTripsCustomer(int customerId, String key) throws TripBookingException {
		CurrentUserSession loggedInUser = sDao.findByUuid(key);
		if (loggedInUser == null) {
			throw new TripBookingException("Please provide a valid key");
		}
		if (customerId == loggedInUser.getUserId()) {
			Optional<Customer> customer = cdao.findById(customerId);
			if (customer.isPresent()) {
				Customer c = customer.get();
				List<TripBooking> tripBooking = c.getTripDetailsList();
				return tripBooking;
			}else {
				throw new TripBookingException("No trip for this customer having id : " + customerId);
			}
			
		} else {
			throw new TripBookingException("Wrong details Please login first!");
		}

	}

	@Override
	public String calculateBill(Integer driverId, String key) throws TripBookingException {
		CurrentUserSession loggedInUser = sDao.findByUuid(key);
		if (loggedInUser == null) {
			throw new TripBookingException("Please provide a valid key to update a customer");
		}
		if (driverId == loggedInUser.getUserId()) {

			Optional<CabDriver> driver = ddao.findById(driverId);
			if (driver.isPresent()) {
				CabDriver cabDriver = driver.get();
				List<TripBooking> customerTripList = cabDriver.getTripDetailsList();

				if (customerTripList.size() == 0)
					throw new TripBookingException("No Trip found");

				TripBooking lastTrip = customerTripList.get(customerTripList.size() - 1);
				if (lastTrip.getStatus() == true)
					throw new TripBookingException("All Trips Completed");

				float ratePerkms = (float) cabDriver.getCab().getRatePerKms();
				float distance = lastTrip.getDistance();

				lastTrip.setTotalFare(distance * ratePerkms);
				cabDriver.setAvailablity(true);
				lastTrip.setStatus(true);

				ddao.save(cabDriver);

				return "Bill is " + lastTrip.getTotalFare();

			} else {
				throw new TripBookingException("Driver does not exist with id" + driverId);
			}

		} else {
			throw new TripBookingException("Wrong details please login first!");
		}

	}

}
