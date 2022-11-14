package com.masai.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.CustomerException;
import com.masai.exception.LoginException;
import com.masai.module.Customer;
import com.masai.module.LoginDTO;
import com.masai.service.CustomerService;
import com.masai.service.LoginService;

@RestController
public class CustomeController {

	@Autowired
	private LoginService logService;
	
	@Autowired
	private CustomerService cService;
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> createCustomerHandler(@Valid @RequestBody Customer customer)throws CustomerException{
		Customer createdCustomer = cService.createCustomer(customer);
		return new ResponseEntity<Customer>(createdCustomer,HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Customer> updateCusomerHandler(@RequestBody Customer customer, @RequestParam("key") String key) throws CustomerException{
		Customer udpatedCustomer = cService.updateCusomer(customer, key);
		return new ResponseEntity<Customer>(udpatedCustomer,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> logIntoAccountHandler(@RequestBody LoginDTO dto) throws LoginException {
		String str = logService.logIntoAccount(dto);
		return new ResponseEntity<String>(str,HttpStatus.ACCEPTED);
	}

	@GetMapping("/logout")
	public ResponseEntity<String> logOutFromAccountHandler(@RequestParam("key") String key) throws LoginException {
		String str = logService.logOutFromAccount(key);
		return new ResponseEntity<String>(str,HttpStatus.ACCEPTED);
	}
	
}
