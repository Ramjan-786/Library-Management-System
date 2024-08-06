package com.lms.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.lms.dao.UserDao;
import com.lms.dtos.UserDto;
import com.lms.entities.User;



@Service

public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDao userRepo;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override  //load by username method extends user details service 
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	   User user = userRepo.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + email);
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				new ArrayList<>());
	}
	
	public  User save(UserDto user) {
		 User newUser = new  User();
		newUser.setFirst_name(user.getFirst_name());
		newUser.setLast_name(user.getLast_name());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setRole(user.getRole());
		
		return userRepo.save(newUser);
	}
}