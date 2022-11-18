package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CustomerException;
import com.masai.module.CabDriver;
import com.masai.module.CurrentUserSession;
import com.masai.module.Customer;
import com.masai.module.TripBooking;
import com.masai.repository.CustomerDAO;
import com.masai.repository.DriverRepo;
import com.masai.repository.SessionDAO;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO cDao;

	@Autowired
	private SessionDAO sDao;

	@Autowired
	private DriverRepo dRepo;

	@Override
	public Customer createCustomer(Customer customer) throws CustomerException {
		Customer existingCustomer = cDao.findByMobile(customer.getMobile());
		if (existingCustomer != null) {
			throw new CustomerException("Customer already registerd with the mobile number");
		}

		return cDao.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer, String key) throws CustomerException {
		CurrentUserSession loggedInUser = sDao.findByUuid(key);
		if (loggedInUser == null) {
			throw new CustomerException("Please provide a valid key to update the customer...");
		}

		// if logged in userId is same as the id of supplied customer which we want to
		// update
		if (customer.getCustomerId() == loggedInUser.getUserId()) {
			return cDao.save(customer);
		} else {
			throw new CustomerException("Invalid customer details, please login first");
		}
	}

	@Override
	public Customer deleteCustomer(Integer customerId, String key) throws CustomerException {
		CurrentUserSession loggedInUser = sDao.findByUuid(key);
		if (loggedInUser == null) {
			throw new CustomerException("please enter a valid key");
		}

		if (customerId == loggedInUser.getUserId()) {
			Optional<Customer> customer = cDao.findById(customerId);
			if (customer.isPresent()) {

				List<TripBooking> tripDetailsList = customer.get().getTripDetailsList();
				if (tripDetailsList.size() > 0) {
					if (tripDetailsList.get(tripDetailsList.size() - 1).getStatus() == false) {
						CabDriver driver = tripDetailsList.get(tripDetailsList.size() - 1).getCabDriver();
						driver.setAvailablity(true);
						dRepo.save(driver);
						tripDetailsList.remove(tripDetailsList.size() - 1);
						cDao.save(customer.get());
					}
				}

				cDao.delete(customer.get());
				sDao.delete(loggedInUser);
				return customer.get();
			} else {
				throw new CustomerException("No customer exist with customer id: " + customerId);
			}
		} else {
			throw new CustomerException("Invalid customer details");
		}

	}

	@Override
	public List<Customer> viewCustomers(String key) throws CustomerException {
		CurrentUserSession loggedInUser = sDao.findByUuid(key);
		if (loggedInUser == null) {
			throw new CustomerException("please enter a valid key");
		}

		List<Customer> customers = cDao.findAll();
		if (customers.size() == 0) {
			throw new CustomerException("Currently there are no customers in the databse");
		} else {
			return customers;
		}
	}

	@Override
	public Customer viewCustomer(Integer customerId, String key) throws CustomerException {
		CurrentUserSession loggedInUser = sDao.findByUuid(key);
		if (loggedInUser == null) {
			throw new CustomerException("please enter a valid key");
		}
		if (loggedInUser.getUserId() == customerId) {
			Optional<Customer> customer = cDao.findById(customerId);
			if (customer.isPresent()) {
				return customer.get();
			} else {
				throw new CustomerException("No customer exist with customer id: " + customerId);
			}
		} else {
			throw new CustomerException("Invalid customer details");
		}

	}

}
