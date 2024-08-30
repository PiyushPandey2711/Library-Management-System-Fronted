package com.nagarro.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.entity.Book;
import com.nagarro.service.BookService;

@Controller
public class DeleteController {

	@Autowired
	BookService bookService;
	
	@PostMapping("/delete")
	public ModelAndView deleteBook(HttpServletRequest request, HttpServletResponse response) {
		
		// Create ModelAndView object
		ModelAndView mv = new ModelAndView();
		
		// Get the bookCode
		Long bookCode = Long.parseLong(request.getParameter("bookCode"));
		// delete Book
		bookService.deleteBook(bookCode);
		// get the session
		HttpSession session = request.getSession();
		List<Book> books = bookService.getAllBooks();
		session.setAttribute("books", books);
		//Return to the home page
		mv.setViewName("home");
		return mv;
		
	}
	
}
