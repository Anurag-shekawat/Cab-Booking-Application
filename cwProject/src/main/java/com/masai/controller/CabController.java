package com.masai.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.CabException;
import com.masai.module.Cab;
import com.masai.service.CabService;

@RestController
@RequestMapping("cab")
public class CabController {

	@Autowired
	private CabService cService;
	
//	@PostMapping("/create")
//	public ResponseEntity<Cab> insertCabHandler(@Valid @RequestBody Cab cab) throws CabException {
//		Cab caby = cService.insertCab(cab);
//		return new ResponseEntity<Cab>(caby,HttpStatus.CREATED);
//	}
	
	@PutMapping("/updateRateOfCab/{cabId}/{rate}")
	public ResponseEntity<Cab> updateCabRatePerKmHandler(@PathVariable Integer cabId,@PathVariable Float rate) throws CabException {
		Cab cab = cService.updateCabRatePerKm(cabId, rate);
		return new ResponseEntity<Cab>(cab,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{cabId}")
	public ResponseEntity<Cab> deleteCabHandler(@PathVariable Integer cabId) throws CabException{
		Cab cab = cService.deleteCab(cabId);
		return new ResponseEntity<Cab>(cab,HttpStatus.OK);
	}
	
	@GetMapping("/getByCarType/{carType}")
	public ResponseEntity<List<Cab>> getCabByCarTypeHandler(@PathVariable String carType) throws CabException{
		List<Cab> cabList = cService.viewCabsOffType(carType);
		return new ResponseEntity<List<Cab>>(cabList,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/countCabs/{carType}")
	public ResponseEntity<Integer> getCountByCarTypeHandler(@PathVariable String carType) throws CabException{
		Integer count = cService.countCabsOfTypes(carType);
		return new ResponseEntity<Integer>(count,HttpStatus.ACCEPTED);
	}

}
