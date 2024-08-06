package com.lms.dao;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lms.entities.Book;

public interface BooksDao extends JpaRepository<Book, Integer> {
	
	Book findById(int id);
	
	List<Book> findBookByCategoryname(String categoryname);
	
	@Query(value = "select book_copies from book where id=:bookId", nativeQuery = true)
	public String getBooksCopies(@Param("bookId") int bookId);
	
	@Transactional
	@Modifying
	@Query(value = "update book set book_copies =:bookCopies where id =:bookId", nativeQuery = true)
	public int updateBooksCopies(@Param("bookCopies") String bookCopies, @Param("bookId") int bookId);

}
