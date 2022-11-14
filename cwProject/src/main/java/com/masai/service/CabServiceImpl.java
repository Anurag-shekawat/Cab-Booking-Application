package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CabException;
import com.masai.module.Cab;
import com.masai.repository.CabRepo;

@Service
public class CabServiceImpl implements CabService {

	@Autowired
	private CabRepo cRepo;

	@Override
	public Cab insertCab(Cab cab) throws CabException {
		if (cab != null) {
			cab.getCabDriver().setCab(cab);
			Cab savedCab = cRepo.save(cab);
			return savedCab;
		} else {
			throw new CabException("Invalid cab details");
		}

	}

	@Override
	public Cab updateCabRatePerKm(Integer cabId, Float rate) throws CabException {
		Optional<Cab> caby = cRepo.findById(cabId);
		if (caby.isPresent()) {
			Cab cab = caby.get();
			cab.setRatePerKms(rate);
			Cab updatedCab = cRepo.save(cab);
			return updatedCab;
		} else {
			throw new CabException("No cab exist with cabId: "+cabId);
		}
	}

	@Override
	public Cab deleteCab(Integer cabId) throws CabException {
		Optional<Cab> caby = cRepo.findById(cabId);
		if (caby.isPresent()) {
			Cab cab = caby.get();
			cab.getCabDriver().setCab(null);
			cRepo.delete(cab);
			return cab;
		} else {
			throw new CabException("No cab exist with cab id: " + cabId);
		}
	}

	@Override
	public List<Cab> viewCabsOffType(String carType) throws CabException {
		List<Cab> cabList = cRepo.findByCarType(carType);
		if (cabList.size() != 0) {
			return cabList;
		} else {
			throw new CabException("No cab exist with carType: " + carType);
		}
	}

	@Override
	public Integer countCabsOfTypes(String carType) throws CabException {
		List<Cab> cabList = cRepo.findByCarType(carType);
		Integer count = cabList.size();
		return count;
	}

}
