package com.masai.service;

import java.util.List;
import java.util.function.Supplier;

import com.masai.exception.DriverException;
import com.masai.module.CabDriver;

public interface DriverService {

	public CabDriver createDriver(CabDriver driver) throws DriverException;
//	public Supplier<CabDriver> updateDriver(CabDriver driver) throws DriverException;
//	public Supplier<CabDriver> deleteDriver(Integer driverId) throws DriverException;
//	public List<CabDriver> viewBestDrivers() throws DriverException;
//	public CabDriver viewDriver(int driverId) throws DriverException;

}
