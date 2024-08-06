package com.lms.services;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.dao.UserDao;
import com.lms.dtos.Credentials;
import com.lms.dtos.DtoEntityConverter;
import com.lms.dtos.UserDto;
//import com.lms.entities.Books;
import com.lms.entities.User;

@Transactional
@Service
public class UserServiceImpl {

	@Autowired
	private UserDao userDao;

	//@Autowired
	//private PasswordEncoder passwordEncoder;

	@Autowired
	private DtoEntityConverter converter;

	//Single User
	public User findUserById(int id) {
		User user = userDao.findById(id);
		return user;
	}

	public UserDto findUserByEmail(String email) {
		User user = userDao.findByEmail(email);
		return converter.toUserDto(user);
	}
	
	//signin
	public UserDto findUserByEmailAndPassword(Credentials cred) {
		User dbuser = userDao.findByEmail(cred.getEmail());
		String rawPassword = cred.getPassword();
		if(dbuser != null) {
			UserDto result = converter.toUserDto(dbuser);
			result.setPassword("*****");
			return result;
		}
		return null;
	}
	
	 
	 
	//signup
	public Map<String, Object> saveUser(UserDto userDto) {
		
		String rawPassword = userDto.getPassword();
		//String encPassword = passwordEncoder.encode(rawPassword);
		//userDto.setPassword(encPassword);
		User user = converter.toUserEntity(userDto);
		user.setRole("Student");
		
		User savedUser = userDao.save(user);
		
		return Collections.singletonMap("insertedId", savedUser.getId());
	}

	//All User
	public List<User> getallUser(){
		return userDao.findAll();
	}

	//Delete User
	public Map<String, Object> deleteUser(int id) {
		if(userDao.existsById(id)) {
			userDao.deleteById(id);
			return Collections.singletonMap("affectedrows", 1);
		}
		return Collections.singletonMap("affectedrows", 0);
	}

	//Update user
	public Map<String, Object> updateUser(User user) {

		//Old data
		User oldData = this.findUserById(user.getId());
		
		User newData = oldData;
		
		if(!oldData.equals(null)) {
			
			newData.setFirst_name(Optional.ofNullable(user.getFirst_name()).orElse(oldData.getFirst_name()));
			newData.setLast_name(Optional.ofNullable(user.getLast_name()).orElse(user.getLast_name()));
			newData.setAddress(Optional.ofNullable(user.getAddress()).orElse(user.getAddress()));
			newData.setContact_no(Optional.ofNullable(user.getContact_no()).orElse(user.getContact_no()));
			newData.setEmail(Optional.ofNullable(user.getEmail()).orElse(user.getEmail()));
			newData.setPassword(Optional.ofNullable(user.getPassword()).orElse(user.getPassword()));
			newData.setBranch(Optional.ofNullable(user.getBranch()).orElse(user.getBranch()));
			newData.setRole("Student");
		}
		
		//Saving Data
		this.userDao.save(newData);

		return Collections.singletonMap("affectedrows", 1);
	}




}
