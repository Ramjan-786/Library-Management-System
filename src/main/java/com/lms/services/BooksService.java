package com.lms.services;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import  com.lms.dao.BooksDao;
import com.lms.entities.Book;

@Service
public class BooksService {
	
	@Autowired
	private BooksDao booksdao;
	
	//Find Book by Id
		public Book findById(int id) {
			Book book = booksdao.findById(id);
			return book;
		}
		
		//Get all books
		public List<Book> getallBooks(){
			return booksdao.findAll();
		}
		
		//Add Book
		public Map<String, Object> saveBook(Book book) {
			Book result = booksdao.save(book);
			return Collections.singletonMap("insertedId", book.getId());
		}
		
		//Delete Book
		public Map<String, Object> deleteBook(int id) {
			if(booksdao.existsById(id)) {
				booksdao.deleteById(id);
				return Collections.singletonMap("affectedrows", 1);
			}
			return Collections.singletonMap("affectedrows", 0);
		}
		
		//find book by category name
		 public List<Book> getAllBooksofCategory(String categoryname){
			 List<Book> books = booksdao.findBookByCategoryname(categoryname);
			 return books;
		 }

		public Map<String, Object> updateBook(Book book) {
			
//			if(!this.findById(book.getId()).equals(null)) {
//				
//				return Collections.singletonMap("Data is Not Available For Id", book.getId());
//			}
			
			try {
				
				//Get oldData
				Book oldBookData = this.findById(book.getId());
				
				Book newBookData = oldBookData;
				
				if(!oldBookData.equals(null)) {
					
					newBookData.setBookname(Optional.ofNullable(book.getBookname()).orElse(oldBookData.getBookname()));
					newBookData.setAuthor(Optional.ofNullable(book.getAuthor()).orElse(oldBookData.getAuthor()));
					newBookData.setPublication(Optional.ofNullable(book.getPublication()).orElse(oldBookData.getPublication()));
					newBookData.setBookcopies(Optional.ofNullable(book.getBookcopies()).orElse(oldBookData.getBookcopies()));
					newBookData.setCategoryname(Optional.ofNullable(book.getCategoryname()).orElse(oldBookData.getCategoryname()));
				}
				
				this.booksdao.save(newBookData);
				
				return Collections.singletonMap("updatedId", book.getId());
				
			} catch (Exception e) {
				e.printStackTrace();
				return Collections.singletonMap("Book Update is Failed For Book Id ", book.getId());
			}
		}

}