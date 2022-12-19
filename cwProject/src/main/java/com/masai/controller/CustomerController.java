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

import com.masai.exception.CustomerException;
import com.masai.exception.LoginException;
import com.masai.exception.TripBookingException;
import com.masai.module.Customer;
import com.masai.module.LoginDTO;
import com.masai.module.TripBooking;
import com.masai.module.TripBookingDTO;
import com.masai.service.CustomerService;
import com.masai.service.LoginService;
import com.masai.service.TripBookingService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private LoginService logService;

	@Autowired
	private CustomerService cService;

	@Autowired
	private TripBookingService tripBookingService;

	@PostMapping("/create")
	public ResponseEntity<Customer> createCustomerHandler(@Valid @RequestBody Customer customer)
			throws CustomerException {
		Customer createdCustomer = cService.createCustomer(customer);
		return new ResponseEntity<Customer>(createdCustomer, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<Customer> updateCusomerHandler(@Valid @RequestBody Customer customer,
			@RequestParam("key") String key) throws CustomerException {
		Customer udpatedCustomer = cService.updateCustomer(customer, key);
		return new ResponseEntity<Customer>(udpatedCustomer, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{cId}")
	public ResponseEntity<Customer> deleteCustomerhandler(@PathVariable("cId") Integer customerId,
			@RequestParam("key") String key) throws CustomerException {
		Customer deletedCustomer = cService.deleteCustomer(customerId, key);
		return new ResponseEntity<Customer>(deletedCustomer, HttpStatus.ACCEPTED);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Customer>> getAllCustomerHandler(@RequestParam("key") String key)
			throws CustomerException {
		List<Customer> customers = cService.viewCustomers(key);
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.ACCEPTED);
	}

	@GetMapping("/getCustomer/{cId}")
	public ResponseEntity<Customer> getCustomerByCIdHandler(@PathVariable("cId") Integer customerId,
			@RequestParam("key") String key) throws CustomerException {
		Customer customer = cService.viewCustomer(customerId, key);
		return new ResponseEntity<Customer>(customer, HttpStatus.ACCEPTED);
	}

	@PostMapping("/createTrip/{key}")
	public ResponseEntity<TripBooking> registerTripBooking(@RequestBody TripBookingDTO tripBooking,
			@PathVariable("key") String key) throws TripBookingException {

		TripBooking savedBooking = tripBookingService.insertTripBooking(tripBooking, key);
		return new ResponseEntity<TripBooking>(savedBooking, HttpStatus.OK);

	}

	@DeleteMapping("/canceltrip/{customerId}/{key}")
	public ResponseEntity<String> deleteTripBooking(@PathVariable Integer customerId, @PathVariable String key)
			throws TripBookingException {

		String deleteBooking = tripBookingService.deleteTripBooking(customerId, key);
		return new ResponseEntity<String>(deleteBooking, HttpStatus.OK);

	}

	@GetMapping("/getAllTrip/{customerid}/{key}")
	public ResponseEntity<List<TripBooking>> allTripBookingByCustomerId(@PathVariable("customerid") Integer customerId,
			@PathVariable("key") String key) throws TripBookingException {

		List<TripBooking> savedBooking = tripBookingService.viewAllTripsCustomer(customerId, key);
		return new ResponseEntity<List<TripBooking>>(savedBooking, HttpStatus.OK);

	}

	@GetMapping("/generateBill/{driverId}/{key}")
	public ResponseEntity<String> generateBillHandler(@PathVariable("driverId") Integer driverId,
			@PathVariable String key) throws TripBookingException {
		String billDetails = tripBookingService.calculateBill(driverId, key);
		return new ResponseEntity<String>(billDetails, HttpStatus.OK);
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
