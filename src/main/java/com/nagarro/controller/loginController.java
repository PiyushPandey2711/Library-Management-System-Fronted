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
import com.nagarro.service.LoginService;

@Controller
public class loginController {
	
	@Autowired
	public
	BookService bookService;
	
	@Autowired
	public
	LoginService loginService;
	
	// Post Mapping
	@PostMapping("/login")
	public ModelAndView validateUser(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView();
		
		// Get the username and password
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username + " " + password);
		// validate the user 
		if(loginService.validateUser(username, password)) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			List<Book> allBooks = bookService.getAllBooks();
			session.setAttribute("books", allBooks);
			mv.setViewName("home");
		}
		else {
			mv.setViewName("index");
		}
		return mv;
	}
}
