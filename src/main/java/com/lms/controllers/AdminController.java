package com.lms.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lms.config.JwtTokenUtil;
import com.lms.dtos.Response;
import com.lms.dtos.UserDto;
import com.lms.entities.JwtRequest;
import com.lms.entities.JwtResponse;
import com.lms.entities.User;
import com.lms.services.JwtUserDetailsService;
import com.lms.services.UserServiceImpl;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserServiceImpl userservice;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUserDetailsService userDetailsService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDto user) throws Exception {
		user.setRole("student");
		return new ResponseEntity<>(userDetailsService.save(user), (HttpStatus.OK));
	}

	private void authenticate(String email, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}
	
//Admin controller me yo mathod ki rakhe user controolerme se 
	//All User
	@GetMapping("/user/showallUsers")
	public ResponseEntity<?> findallUsers(){
		List<User> result = userservice.getallUser();
		return Response.success(result);
	}
	
	//Delete User
	@DeleteMapping("/user/delete/{id}")
	public ResponseEntity<?> deleteuser(@PathVariable("id") int id){
	Map<String, Object> result = userservice.deleteUser(id);
	return Response.success(result);
		}

		//Single User
	@GetMapping("/user/details/{id}")
	public ResponseEntity<?> findUserById(@PathVariable("id") int id) {
	User result = userservice.findUserById(id);
	return Response.success(result);
		}
	
	
		
		

}
