package com.masai.service;

import java.util.List;

import com.masai.exception.DriverException;
import com.masai.module.CabDriver;

public interface DriverService {

	public CabDriver createDriver(CabDriver driver) throws DriverException;
	public CabDriver updateDriver(CabDriver driver, String key) throws DriverException;
	public CabDriver deleteDriver(Integer driverId, String key) throws DriverException;
	public List<CabDriver> viewBestDrivers(Integer driverId, String key) throws DriverException;
	public CabDriver viewDriver(Integer driverId,String key) throws DriverException;

}
