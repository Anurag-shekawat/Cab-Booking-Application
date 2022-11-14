package com.masai.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.LoginException;
import com.masai.module.CabDriver;
import com.masai.module.CurrentUserSession;
import com.masai.module.Customer;
import com.masai.module.LoginDTO;
import com.masai.repository.CustomerDAO;
import com.masai.repository.DriverRepo;
import com.masai.repository.SessionDAO;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private CustomerDAO cDao;

	@Autowired
	private DriverRepo driverDao;

	@Autowired
	private SessionDAO sDao;

	@Override
	public String logIntoAccount(LoginDTO dto) throws LoginException {

		if (dto.getRole().equalsIgnoreCase("customer")) {
			Customer existingCustomer = cDao.findByMobile(dto.getMobileNo());
			if (existingCustomer == null) {
				throw new LoginException("Please enter a valid mobile number...");
			}

			Optional<CurrentUserSession> validCustomerSessionOpt = sDao.findById(existingCustomer.getCustomerId());

			if (validCustomerSessionOpt.isPresent()) {
				throw new LoginException("User already loged in");
			}

			if (existingCustomer.getPassword().equals(dto.getPassword())) {
				String key = RandomString.make(6);
				CurrentUserSession currentUserSession = new CurrentUserSession(existingCustomer.getCustomerId(), key,
						LocalDateTime.now());
				sDao.save(currentUserSession);
				return currentUserSession.toString();
			} else {
				throw new LoginException("Please enter a valid password");
			}
		}else if(dto.getRole().equalsIgnoreCase("driver")) {
			CabDriver existingDriver = driverDao.findByMobile(dto.getMobileNo());
			if(existingDriver==null) {
				throw new LoginException("Please enter a valid mobile number...");
			}
			
			Optional<CurrentUserSession> validDrvierSessionOpt = sDao.findById(existingDriver.getDriverId());
			
			if(validDrvierSessionOpt.isPresent()) {
				throw new LoginException("User already loged in");
			}
			
			if(existingDriver.getPassword().equals(dto.getPassword())) {
				String key = RandomString.make(6);
				CurrentUserSession currentUserSession = new CurrentUserSession(existingDriver.getDriverId(),key,LocalDateTime.now());
				sDao.save(currentUserSession);
				return currentUserSession.toString();
			}else {
				throw new LoginException("Please enter a valid password");
			}
		}else {
			throw new LoginException("Please select role among the following:- Admin/Driver/Customer");
		}

	}

	@Override
	public String logOutFromAccount(String key) throws LoginException {
		CurrentUserSession validCustomerSession = sDao.findByUuid(key);
		if (validCustomerSession == null) {
			throw new LoginException("User is not logged in");
		} else {
			sDao.delete(validCustomerSession);
			return "Logged Out !";
		}
	}

}
