package com.aster.app.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aster.app.entity.Customer;
import com.aster.app.service.CustomerServiceImpl;

@Service
public class CustomerDetailsService implements UserDetailsService {
	
	private UserDetails user=null;
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer customer=this.customerServiceImpl.getCustomerByEmail(email);
		if(customer!=null) {
			List<GrantedAuthority> roles=new ArrayList<>();
			roles.add(new SimpleGrantedAuthority(customer.getRole()));
			user=User.withUsername(customer.getEmail()).password(customer.getPassword())
					.authorities(roles).build();
			return user;
			
		}
		else {
			throw new UsernameNotFoundException("Customer with this email was not found");
		}
	}

}
