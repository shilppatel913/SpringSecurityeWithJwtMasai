package com.aster.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aster.app.entity.Customer;
import com.aster.app.service.CustomerServiceImpl;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	@Autowired
	private PasswordEncoder encoder;

	@PostMapping("/customers")
	public ResponseEntity<Customer> regCustomer(@RequestBody Customer customer){
		String hash=encoder.encode(customer.getPassword());
		customer.setPassword(hash);
		Customer cust=this.customerServiceImpl.registerCustomer(customer);
		return new ResponseEntity<Customer>(cust,HttpStatus.CREATED);
	}
	
	@GetMapping("/customers/{email}")
	public ResponseEntity<Customer> getCustomerByEmailHandler(@PathVariable("email") String email){
		Customer customer=this.customerServiceImpl.getCustomerByEmail(email);
		return new ResponseEntity<Customer>(customer,HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCust(){
		List<Customer> customers=this.customerServiceImpl.getAllCustomers();
		return new ResponseEntity<List<Customer>>(customers,HttpStatus.ACCEPTED);
	}
}
