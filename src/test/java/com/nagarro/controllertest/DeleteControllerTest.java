package com.nagarro.controllertest;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.controller.DeleteController;
import com.nagarro.entity.Book;
import com.nagarro.service.BookService;

public class DeleteControllerTest {
	
	@InjectMocks
    private DeleteController deleteController;

    @Mock
    private BookService bookService;

    @Mock
    private Book book;

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
    public void testDeleteBook() throws Exception {
        // Mock request parameters
        when(request.getParameter("bookCode")).thenReturn("1");

        
        List<Book> mockBooks = Arrays.asList(new Book(1L, "Book 1", "Author 1", "2023-06-20"),
                                              new Book(2L, "Book 2", "Author 2", "2023-06-21"));
        when(bookService.getAllBooks()).thenReturn(mockBooks);

        // Call the method to test
        ModelAndView mv = deleteController.deleteBook(request, response);

       
        verify(bookService, times(1)).deleteBook(1L);
        verify(session, times(1)).setAttribute("books", mockBooks);

        // Assertions to verify the results
        assertNotNull(mv);
        assertEquals("home", mv.getViewName());
    }

}
