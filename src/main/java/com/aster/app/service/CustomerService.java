package com.aster.app.service;

import java.util.List;

import com.aster.app.entity.Customer;

public interface CustomerService {

	public Customer registerCustomer(Customer customer);
	public Customer getCustomerByEmail(String email);
	public List<Customer> getAllCustomers();

}
