package com.lms.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.dao.BookDao;
import com.lms.dtos.Response;
import com.lms.entities.Book;
import com.lms.services.BooksService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BooksService bookserviceI;

	//Show all books
	@GetMapping("/showallbooks")
	public ResponseEntity<?> findallBooks(){
		List<Book> result = bookserviceI.getallBooks();
		return Response.success(result);
	}

	//Delete Book
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletebook(@PathVariable("id") int id){
		Map<String, Object> result = bookserviceI.deleteBook(id);
		return Response.success(result);
	}

	//Find Book by Id
	@GetMapping("/details/{id}")
	public ResponseEntity<?> findBookById(@PathVariable("id") int id) {
		Book result = bookserviceI.findById(id);
		return Response.success(result);
	}

	//Add Book
	@PostMapping("/add")
	public ResponseEntity<?> addBook(@RequestBody Book book) {
		Map<String, Object> result = bookserviceI.saveBook(book);
		return Response.success(result);
	}

	// Show all books by category name
	@GetMapping("/find/{categoryname}")
	public ResponseEntity<?> findBookByCategoryname(@PathVariable("categoryname") String categoryname){
		List<Book> allbooksofCategory = bookserviceI.getAllBooksofCategory(categoryname);
		return Response.success(allbooksofCategory);
	}
/*
	//update book
	@PutMapping("/updateBook/{id}")
	public ResponseEntity<?> updateBook(@PathVariable("id") @RequestBody Book book) {
		Map<String, Object> result = bookserviceI.updateBook(book);

		if(result.toString().contains("Failed")) {
			return Response.error(result);
		}

		return Response.success(result);
	}
	*/
	@PutMapping("/updatebook/{id}")
	public ResponseEntity<Map<String,Object>> updateBook(@PathVariable Integer id, @RequestBody Book bookDetails){
		Book book = bookserviceI.findById(id);
		
		book.setBookname(bookDetails.getBookname());
		book.setAuthor(bookDetails.getAuthor());
		book.setPublication(bookDetails.getPublication());
		book.setCategoryname(bookDetails.getCategoryname());
		book.setBookcopies(bookDetails.getBookcopies());
		
		
		
		Map<String, Object> updatedBook = bookserviceI.saveBook(book);
		return ResponseEntity.ok(updatedBook);
	}
	
	/*
	  //update book--->By Nehal
	@PutMapping("/updateBook")
	public ResponseEntity<?> updateBook(@RequestBody Book book) {
		Map<String, Object> result = bookserviceI.updateBook(book);

		if(result.toString().contains("Failed")) {
			return Response.error(result);
		}

		return Response.success(result);
	}
	*/

}

