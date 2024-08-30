package com.nagarro.entity;

import org.springframework.stereotype.Component;

@Component
public class Book {

	private long bookCode;
	private String bookName;
	private String author;
	private String dataAddedOn;
	
	//Default Constructor
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//Parameterized Constructor
	public Book(long bookCode, String bookName, String author, String dataAddedOn) {
		super();
		this.bookCode = bookCode;
		this.bookName = bookName;
		this.author = author;
		this.dataAddedOn = dataAddedOn;
	}

	//Getters and Setter
	public long getBookCode() {
		return bookCode;
	}

	public void setBookCode(long bookCode) {
		this.bookCode = bookCode;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDataAddedOn() {
		return dataAddedOn;
	}

	public void setDataAddedOn(String dataAddedOn) {
		this.dataAddedOn = dataAddedOn;
	}
}
