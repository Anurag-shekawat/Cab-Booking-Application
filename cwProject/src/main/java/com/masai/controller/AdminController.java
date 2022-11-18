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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.AdminException;
import com.masai.exception.CabException;
import com.masai.exception.LoginException;
import com.masai.module.Admin;
import com.masai.module.Cab;
import com.masai.module.LoginDTO;
import com.masai.module.TripBooking;
import com.masai.service.AdminService;
import com.masai.service.CabService;
import com.masai.service.LoginService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private LoginService logService;

	@Autowired
	private AdminService aService;

	@Autowired
	private CabService cService;

	@PostMapping("/create")
	public ResponseEntity<Admin> createAdminHandler(@Valid @RequestBody Admin admin) throws AdminException {
		Admin createdAdmin = aService.insertAdmin(admin);
		return new ResponseEntity<Admin>(createdAdmin, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<Admin> updateAdminHandler(@Valid @RequestBody Admin admin, @RequestParam("key") String key)
			throws AdminException {
		Admin udpatedAdmin = aService.updateAdmin(admin, key);
		return new ResponseEntity<Admin>(udpatedAdmin, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{aId}")
	public ResponseEntity<Admin> deleteAdminhandler(@PathVariable("aId") Integer adminId,
			@RequestParam("key") String key) throws AdminException {
		Admin deletedAdmin = aService.deleteAdmin(adminId, key);
		return new ResponseEntity<Admin>(deletedAdmin, HttpStatus.ACCEPTED);
	}

	@GetMapping("/getAllTripsByCab/{cabId}/{adminId}")
	public ResponseEntity<List<TripBooking>> getAllTripsByCabHandler(@PathVariable("cabId") Integer cabId,
			@PathVariable("adminId") Integer adminId, @RequestParam("key") String key) throws AdminException {

		List<TripBooking> list = aService.getAllTripsByCab(cabId, adminId, key);
		return new ResponseEntity<List<TripBooking>>(list, HttpStatus.ACCEPTED);

	}

	@GetMapping("/getAllTrips/{adminId}")
	public ResponseEntity<List<TripBooking>> getAllTripsHandler(@PathVariable("adminId") Integer adminId,
			@RequestParam("key") String key) throws AdminException {
		List<TripBooking> list = aService.getAllTrips(adminId, key);
		return new ResponseEntity<List<TripBooking>>(list, HttpStatus.ACCEPTED);
	}

	@PostMapping("/login")
	public ResponseEntity<String> logIntoAccountHandler(@Valid @RequestBody LoginDTO dto) throws LoginException {
		String str = logService.logIntoAccount(dto);
		return new ResponseEntity<String>(str, HttpStatus.ACCEPTED);
	}

	@GetMapping("/logout")
	public ResponseEntity<String> logOutFromAccountHandler(@RequestParam("key") String key) throws LoginException {
		String str = logService.logOutFromAccount(key);
		return new ResponseEntity<String>(str, HttpStatus.ACCEPTED);
	}

	@PutMapping("/updateRateOfCab/{cabId}/{rate}")
	public ResponseEntity<Cab> updateCabRatePerKmHandler(@PathVariable Integer cabId, @PathVariable Float rate)
			throws CabException {
		Cab cab = cService.updateCabRatePerKm(cabId, rate);
		return new ResponseEntity<Cab>(cab, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{cabId}")
	public ResponseEntity<Cab> deleteCabHandler(@PathVariable Integer cabId) throws CabException {
		Cab cab = cService.deleteCab(cabId);
		return new ResponseEntity<Cab>(cab, HttpStatus.OK);
	}

	@GetMapping("/getCabByCarType/{carType}")
	public ResponseEntity<List<Cab>> getCabByCarTypeHandler(@PathVariable String carType) throws CabException {
		List<Cab> cabList = cService.viewCabsOffType(carType);
		return new ResponseEntity<List<Cab>>(cabList, HttpStatus.ACCEPTED);
	}

	@GetMapping("/countCabs/{carType}")
	public ResponseEntity<Integer> getCountByCarTypeHandler(@PathVariable String carType) throws CabException {
		Integer count = cService.countCabsOfTypes(carType);
		return new ResponseEntity<Integer>(count, HttpStatus.ACCEPTED);
	}

}
