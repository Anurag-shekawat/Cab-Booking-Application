package com.masai.service;

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
	public Customer updateCusomer(Customer customer, String key) throws CustomerException {
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

}
