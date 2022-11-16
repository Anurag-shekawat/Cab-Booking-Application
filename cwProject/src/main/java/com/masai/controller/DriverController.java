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

import com.masai.exception.DriverException;
import com.masai.exception.LoginException;
import com.masai.module.CabDriver;
import com.masai.module.DriverDto;
import com.masai.module.LoginDTO;
import com.masai.service.DriverService;
import com.masai.service.LoginService;

@RestController
@RequestMapping("/driver")
public class DriverController {

	@Autowired
	private DriverService dService;

	@Autowired
	private LoginService logService;

	@PostMapping("/create")
	public ResponseEntity<CabDriver> createDriverHandler(@RequestBody CabDriver cabDriver) throws DriverException {
		CabDriver cd = dService.createDriver(cabDriver);
		return new ResponseEntity<CabDriver>(cd, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<CabDriver> updateDriverHandler(@Valid @RequestBody DriverDto driver,
			@RequestParam("key") String key) throws DriverException {
		CabDriver cabDriver = dService.updateDriver(driver, key);
		return new ResponseEntity<CabDriver>(cabDriver, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{dId}")
	public ResponseEntity<CabDriver> deleteCustomerhandler(@PathVariable("dId") Integer driverId,
			@RequestParam("key") String key) throws DriverException {
		CabDriver driver = dService.deleteDriver(driverId, key);
		return new ResponseEntity<CabDriver>(driver, HttpStatus.ACCEPTED);
	}

	@GetMapping("/viewBestDrivers/{dId}")
	public ResponseEntity<List<CabDriver>> viewBestDriverHandler(@PathVariable("dId") Integer driverId,
			@RequestParam("key") String key) throws DriverException {
		List<CabDriver> cbList = dService.viewBestDrivers(driverId,key);
		return new ResponseEntity<List<CabDriver>>(cbList, HttpStatus.ACCEPTED);
	}

	@GetMapping("/viewDriver/{dId}")
	public ResponseEntity<CabDriver> viewDriverHandler(@PathVariable("dId") Integer driverId,
			@RequestParam("key") String key) throws DriverException {
		CabDriver driver = dService.viewDriver(driverId, key);
		return new ResponseEntity<CabDriver>(driver, HttpStatus.ACCEPTED);
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

}
