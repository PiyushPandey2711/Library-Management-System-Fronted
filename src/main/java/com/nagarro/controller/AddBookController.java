package com.nagarro.controller;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.entity.Author;
import com.nagarro.entity.Book;
import com.nagarro.service.AuthorService;
import com.nagarro.service.BookService;

@Controller
public class AddBookController {
	
	
	@Autowired
	AuthorService authorService;
	
	@Autowired
	BookService bookService;

	@Autowired
	Book book;
	
	@PostMapping("/add")
	public ModelAndView editBook(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();
		
		// Get date
		String date = java.time.LocalDate.now().toString();
		LocalDate currentDate = LocalDate.parse(date);

		// get day from date
		int day = currentDate.getDayOfMonth();

		// Get month from date
		Month month = currentDate.getMonth();

		// Get year from date
		int year = currentDate.getYear();

		String Date = day + " " + month + " " + year;
		// Create the object of author 
		List<Author> author = authorService.getAllAuthors();
		System.out.println(author.get(0));

		mv.addObject("Author", author);
		mv.addObject("date", Date);
		mv.setViewName("addBook");

		return mv;
	} 

	@PostMapping("/addNewBook")
	public ModelAndView addBook(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();

		// get parameters
		Long bookCode = Long.parseLong(request.getParameter("bookCode"));
		String bookName = request.getParameter("bookName");
		String author = request.getParameter("author");
		String dataAddedOn = request.getParameter("addedOn");

		//Set the book details
		book.setBookCode(bookCode);
		book.setBookName(bookName);
		book.setAuthor(author);
		book.setDataAddedOn(dataAddedOn);

		System.out.println(book);
		//Save the book
		bookService.saveBook(book);
		
		//Get session
		HttpSession session = request.getSession();
		
		List<Book> books = bookService.getAllBooks();
		session.setAttribute("books", books);
		
		// Go back to home page
		mv.setViewName("home");

		return mv;
	}
	
}
