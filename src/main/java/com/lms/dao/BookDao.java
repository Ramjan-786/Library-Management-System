package com.lms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lms.entities.Book;

public interface BookDao extends JpaRepository<Book, Integer> {
	
	Book findById(int id);
	
	//@Query ("Select * from books where bookname = ?1")
	//Books findByNameContaining(String bookname);
	

}
