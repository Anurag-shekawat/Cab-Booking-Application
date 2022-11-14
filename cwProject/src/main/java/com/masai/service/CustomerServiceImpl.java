package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CustomerException;
import com.masai.module.CurrentUserSession;
import com.masai.module.Customer;
import com.masai.repository.CustomerDAO;
import com.masai.repository.SessionDAO;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO cDao;
	
	@Autowired
	private SessionDAO sDao;
	
	@Override
	public Customer createCustomer(Customer customer) throws CustomerException {
		Customer existingCustomer = cDao.findByMobile(customer.getMobile());
		if(existingCustomer!=null) {
			throw new CustomerException("Customer already registerd with the mobile number");
		}
		
		return cDao.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer, String key) throws CustomerException {
		CurrentUserSession loggedInUser = sDao.findByUuid(key);
		if(loggedInUser==null) {
			throw new CustomerException("Please provide a valid key to update the customer...");
		}
		
		//if logged in userId is same as the id of supplied customer which we want to update
		if(customer.getCustomerId()==loggedInUser.getUserId()) {
			return cDao.save(customer);
		}else {
			throw new CustomerException("Invalid customer details, please login first");
		}
	}

	@Override
	public Customer deleteCustomer(Integer customerId) throws CustomerException {
		Optional<Customer> customer = cDao.findById(customerId);
		if(customer.isPresent()) {
			cDao.delete(customer.get());
			Optional<CurrentUserSession> cus = sDao.findById(customerId);
			if(cus.isPresent()) {
				sDao.delete(cus.get());
			}
			return customer.get();
		}else {
			throw new CustomerException("No customer exist with customer id: "+customerId);
		}
	}

	@Override
	public List<Customer> viewCustomers() throws CustomerException {
		List<Customer> customers = cDao.findAll();
		if(customers.size()==0) {
			throw new CustomerException("Currently there are no customers in the databse");
		}else {
			return customers;
		}
	}

	@Override
	public Customer viewCustomer(Integer customerId) throws CustomerException {
		Optional<Customer> customer	= cDao.findById(customerId);
		if(customer.isPresent()) {
			return customer.get();
		}else {
			throw new CustomerException("No customer exist with customer id: "+customerId);
		}
	}

}
