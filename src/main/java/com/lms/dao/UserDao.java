package com.lms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.entities.User;

public interface UserDao extends JpaRepository<User, Integer> {
	User findById(int id);
	User findByEmail(String email);
	
}
