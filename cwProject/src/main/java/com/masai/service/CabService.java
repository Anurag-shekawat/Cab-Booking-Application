package com.masai.service;

import java.util.List;

import com.masai.exception.CabException;
import com.masai.module.Cab;

public interface CabService {

	public Cab insertCab(Cab cab) throws CabException;
	public Cab updateCabRatePerKm(Integer cabId, Float rate) throws CabException;
	public Cab deleteCab(Integer cabId) throws CabException;
	public List<Cab> viewCabsOffType(String carType) throws CabException;
	public Integer countCabsOfTypes(String carType) throws CabException;
	
}
