package com.aster.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aster.app.entity.Customer;
import com.aster.app.repo.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Customer registerCustomer(Customer customer) {
		return this.customerRepository.save(customer);
	}

	@Override
	public Customer getCustomerByEmail(String email) {
		Optional<Customer> customer=this.customerRepository.findByEmail(email);
		if(!customer.isEmpty()) {
			return customer.get();
		}
		else {
			System.out.println("Customer could not be found please register the customer");
			return null;
		}
	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return this.customerRepository.findAll();
	}

}
