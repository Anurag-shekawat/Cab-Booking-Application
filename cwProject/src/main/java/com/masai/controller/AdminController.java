package com.masai.controller;

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
import com.masai.exception.CustomerException;
import com.masai.exception.LoginException;
import com.masai.module.Admin;
import com.masai.module.Customer;
import com.masai.module.LoginDTO;
import com.masai.service.AdminService;
import com.masai.service.LoginService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private LoginService logService;
	
	@Autowired
	private AdminService aService;
	
	@PostMapping("/create")
	public ResponseEntity<Admin> createAdminHandler(@Valid @RequestBody Admin admin)
			throws AdminException {
		Admin createdAdmin = aService.insertAdmin(admin);
		return new ResponseEntity<Admin>(createdAdmin, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<Admin> updateAdminHandler(@Valid @RequestBody Admin admin,
			@RequestParam("key") String key) throws AdminException {
		Admin udpatedAdmin = aService.updateAdmin(admin, key);
		return new ResponseEntity<Admin>(admin, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{aId}")
	public ResponseEntity<Admin> deleteCustomerhandler(@PathVariable("aId") Integer adminId,
			@RequestParam("key") String key) throws AdminException {
		Admin deletedAdmin = aService.deleteAdmin(adminId, key);
		return new ResponseEntity<Admin>(deletedAdmin, HttpStatus.ACCEPTED);
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
