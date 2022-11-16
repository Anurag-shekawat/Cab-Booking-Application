package com.masai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.DriverException;
import com.masai.module.CabDriver;
import com.masai.module.CurrentUserSession;
import com.masai.repository.DriverRepo;
import com.masai.repository.SessionDAO;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	private DriverRepo dDao;

	@Autowired
	private SessionDAO sDao;

	@Override
	public CabDriver createDriver(CabDriver driver) throws DriverException {
		CabDriver existingDriver = dDao.findByMobile(driver.getMobile());
		if (existingDriver != null) {
			throw new DriverException("Driver already registerd with the mobile number");
		} else {
			driver.getCab().setCabDriver(driver);
			return dDao.save(driver);
		}

	}

	@Override
	public CabDriver updateDriver(CabDriver driver, String key) throws DriverException {
		CurrentUserSession loggedInDriver = sDao.findByUuid(key);
		if (loggedInDriver == null) {
			throw new DriverException("Please enter a valid key");
		}

		if (driver.getDriverId() == loggedInDriver.getUserId()) {
			driver.setCab(driver.getCab());
			driver.getCab().setCabDriver(driver);
			return dDao.save(driver);
		} else {
			throw new DriverException("Invalid driver details");
		}
	}

	@Override
	public CabDriver deleteDriver(Integer driverId, String key) throws DriverException {
		CurrentUserSession loggedInDriver = sDao.findByUuid(key);
		if (loggedInDriver == null) {
			throw new DriverException("Please enter a valid key");
		}

		if (driverId == loggedInDriver.getUserId()) {
			Optional<CabDriver> driver = dDao.findById(driverId);
			if (driver.isPresent()) {
				driver.get().getCab().setCabDriver(null);
				dDao.delete(driver.get());
				return driver.get();
			} else {
				throw new DriverException("No driver exist with driverId: " + driverId);
			}
		} else {
			throw new DriverException("Invalid driver details");
		}

	}

	@Override
	public List<CabDriver> viewBestDrivers(Integer driverId, String key) throws DriverException {
		CurrentUserSession loggedInDriver = sDao.findByUuid(key);
		if (loggedInDriver == null) {
			throw new DriverException("Please enter a valid key");
		}

		if (driverId == loggedInDriver.getUserId()) {
			List<CabDriver> driverList = dDao.findAll();
			List<CabDriver> bestDrivers = new ArrayList<>();
			for (CabDriver cd : driverList) {
				if (cd.getRating() >= 4.5) {
					bestDrivers.add(cd);
				}
			}
			if (bestDrivers.size() == 0) {
				throw new DriverException("No driver found with rating greater than or equal to 4.5");
			}
			return bestDrivers;
		}else {
			throw new DriverException("Invalid driver details"); 
		}
	}

	@Override
	public CabDriver viewDriver(Integer driverId, String key) throws DriverException {
		CurrentUserSession loggedInDriver = sDao.findByUuid(key);
		if (loggedInDriver == null) {
			throw new DriverException("Please enter a valid key");
		}

		if (driverId == loggedInDriver.getUserId()) {
			Optional<CabDriver> driver = dDao.findById(driverId);
			if (driver.isPresent()) {
				return driver.get();
			} else {
				throw new DriverException("No driver exist with driver id: " + driverId);
			}
		} else {
			throw new DriverException("Invalid driver details");
		}
	}

}
