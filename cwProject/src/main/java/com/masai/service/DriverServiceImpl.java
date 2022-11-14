package com.masai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.DriverException;
import com.masai.module.CabDriver;
import com.masai.repository.DriverRepo;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	private DriverRepo dDao;

	@Override
	public CabDriver createDriver(CabDriver driver) throws DriverException {
		CabDriver existingDriver = dDao.findByMobile(driver.getMobile());
		if (existingDriver != null) {
			throw new DriverException("Driver already registerd with the mobile number");
		}

		return dDao.save(driver);
	}

}
