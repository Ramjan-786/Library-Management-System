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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lms.config.JwtTokenUtil;
import com.lms.dtos.Credentials;
import com.lms.dtos.Response;
import com.lms.dtos.UserDto;
import com.lms.entities.Book;
import com.lms.entities.JwtRequest;
import com.lms.entities.JwtResponse;
import com.lms.entities.User;
import com.lms.services.JwtUserDetailsService;
import com.lms.services.UserServiceImpl;

 //controller + responsebody
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {
	
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
	
	
	
	
	//signin
	@PostMapping("/signin")
	public ResponseEntity<?> signin(@RequestBody Credentials cred){
		UserDto userDto = userservice.findUserByEmailAndPassword(cred);
		if (userDto == null)
			return Response.error("user not found");
		return Response.success(userDto);	
	}
	
	
	//signup  ADD USER
	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@RequestBody UserDto userDto) {
		//userDto.setrole("student");
		Map<String, Object> result = userservice.saveUser(userDto);
		return Response.success(result);
	}
	
	//Single User
	@GetMapping("/details/{id}")
	public ResponseEntity<?> findUserById(@PathVariable("id") int id) {
		User result = userservice.findUserById(id);
		return Response.success(result);
	}
	
	//update user
	@PutMapping("/updateuser/{id}")
	public ResponseEntity<Map<String,Object>> updateBook(@PathVariable Integer id, @RequestBody UserDto userDetails){
		User user = userservice.findUserById(id);
		
		user.setFirst_name(userDetails.getFirst_name());
		user.setLast_name(userDetails.getLast_name());
		user.setAddress(userDetails.getAddress());
		user.setContact_no(userDetails.getContact_no());
		user.setBranch(userDetails.getBranch());
		user.setEmail(userDetails.getEmail());
		
			
		
		Map<String, Object> updateduser = userservice.saveUser(userDetails);//wrong
		return ResponseEntity.ok(updateduser);
	}
	
	//what is this
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

	
}
