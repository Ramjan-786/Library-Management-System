package com.lms.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "book")
public class Book {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String bookname;
	
	@Column
	private String author;
	
	@Column
	private String publication;
	
	@Column(name = "book_copies")
	private String bookcopies;
	
	@Column(name = "categoryname")
	private String categoryname;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getBookname() {
		return bookname;
	}
	
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getPublication() {
		return publication;
	}
	
	public void setPublication(String publication) {
		this.publication = publication;
	}
	
	public String getBookcopies() {
		return bookcopies;
	}
	
	public void setBookcopies(String bookcopies) {
		this.bookcopies = bookcopies;
	}
	
	public String getCategoryname() {
		return categoryname;
	}
	
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", bookname=" + bookname + ", author=" + author + ", publication=" + publication
				+ ", bookcopies=" + bookcopies + ", categoryname=" + categoryname + "]";
	}
	
	public Book(int id, String bookname, String author, String publication, String bookcopies, String categoryname) {
		super();
		this.id = id;
		this.bookname = bookname;
		this.author = author;
		this.publication = publication;
		this.bookcopies = bookcopies;
		this.categoryname = categoryname;
	}
	
	public Book() {
		super();
	}
	
	

}
