package com.nagarro.controller;

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
public class EditController {

	@Autowired
	AuthorService authorService;

	@Autowired
	BookService bookService;

	@Autowired
	Book book;

	@PostMapping("/edit")
	public ModelAndView editBook(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();

		// Set the values
		Long bookCode = Long.parseLong(request.getParameter("bookCode"));
		String bookName = request.getParameter("bookName");
		String author = request.getParameter("author");
		String dataAddedOn = request.getParameter("dataAddedOn");

		book.setBookCode(bookCode);
		book.setBookName(bookName);
		book.setDataAddedOn(dataAddedOn);
		book.setAuthor(author);

		mv.addObject("book", book);

		List<Author> allAuthors = authorService.getAllAuthors();

		mv.addObject("Author", allAuthors);

		mv.setViewName("editBook");

		return mv;
	}

	@PostMapping("/editBook")
	public ModelAndView addBook(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();

		// get parameters
		Long bookCode = Long.parseLong(request.getParameter("bookCode"));
		String bookName = request.getParameter("bookName");
		String author = request.getParameter("author");
		String dataAddedOn = request.getParameter("dataAddedOn");
		

		book.setBookCode(bookCode);
		book.setBookName(bookName);
		book.setDataAddedOn(dataAddedOn);
		book.setAuthor(author);

		
		bookService.saveBook(book);
		
		HttpSession session = request.getSession();
		List<Book> books = bookService.getAllBooks();
		session.setAttribute("books", books);

		mv.setViewName("home");

		return mv;
	}
}
