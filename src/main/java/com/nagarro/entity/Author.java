package com.nagarro.entity;

import org.springframework.stereotype.Component;

@Component
public class Author {

	private String authorName;

	public Author() {
		super();
	}

	public Author(String authorName) {
		super();
		this.authorName = authorName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}	
}