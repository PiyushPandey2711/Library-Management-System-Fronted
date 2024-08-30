package com.nagarro.controllertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.service.BookService;
import com.nagarro.service.LoginService;
import com.nagarro.controller.loginController;
import com.nagarro.entity.Book;

public class LoginControllerTest {

    @InjectMocks
    private loginController loginController;

    @Mock
    private BookService bookService;

    @Mock
    private LoginService loginService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void testValidateUserSuccess() {
        
        when(request.getParameter("username")).thenReturn("testUser");
        when(request.getParameter("password")).thenReturn("testPassword");

        when(loginService.validateUser("testUser", "testPassword")).thenReturn(true);
        
        // Call the method to test
        ModelAndView mv = loginController.validateUser(request, response);

        // Assertions to verify the result
        assertEquals("home", mv.getViewName(), "View name should be 'index'");
    }

    @Test
    public void testValidateUserFailure() {
        // Mock HttpServletRequest to simulate behavior
        when(request.getParameter("username")).thenReturn("testUser");
        when(request.getParameter("password")).thenReturn("wrongPassword");

        // Mock LoginService to return false for invalid user
        when(loginService.validateUser("testUser", "wrongPassword")).thenReturn(false);

        // Call the method to test
        ModelAndView mv = loginController.validateUser(request, response);

        // Assertions to verify the results
        assertEquals("index", mv.getViewName(), "View name should be 'index'");
    }
}
