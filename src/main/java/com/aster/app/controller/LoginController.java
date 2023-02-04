package com.aster.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aster.app.entity.Customer;
import com.aster.app.service.CustomerServiceImpl;

@RestController
public class LoginController {

	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	@GetMapping("/signIn")
	public ResponseEntity<Customer> getLoggedInCustomer(Authentication auth){
		Customer customer=this.customerServiceImpl.getCustomerByEmail(auth.getName());
		return new ResponseEntity<Customer>(customer,HttpStatus.ACCEPTED);
	}
}
