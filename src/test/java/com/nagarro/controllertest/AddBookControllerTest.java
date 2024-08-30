package com.nagarro.controllertest;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.Month;
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

import com.nagarro.controller.AddBookController;
import com.nagarro.entity.Author;
import com.nagarro.entity.Book;
import com.nagarro.service.AuthorService;
import com.nagarro.service.BookService;

public class AddBookControllerTest {

    @InjectMocks
    private AddBookController addBookController;

    @Mock
    private AuthorService authorService;

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
    public void testAddBook() throws Exception {
        // Mock request parameters
        when(request.getParameter("bookCode")).thenReturn("1");
        when(request.getParameter("bookName")).thenReturn("Book 1");
        when(request.getParameter("author")).thenReturn("Author 1");
        when(request.getParameter("addedOn")).thenReturn("2023-06-20");

        // Call the method to test
        ModelAndView mv = addBookController.addBook(request, response);

         
        verify(book).setBookCode(1L);
        verify(book).setBookName("Book 1");
        verify(book).setAuthor("Author 1");
        verify(book).setDataAddedOn("2023-06-20");

        // Assertions to verify the results
        assertNotNull(mv);
    }
    @Test
    public void testEditBook() {
        // Mock the current date
        LocalDate currentDate = LocalDate.of(2024, Month.JULY, 9);
        String expectedDate = "9 JULY 2024";

        // Mock the authors list
        List<Author> authors = Arrays.asList(new Author("Author1"), new Author("Author2"));
        when(authorService.getAllAuthors()).thenReturn(authors);

        // Call the method to test
        ModelAndView mv = addBookController.editBook(request, response);

        // Verify the results
        assertEquals("addBook", mv.getViewName(), "View name should be 'addBook'");
        assertEquals(authors, mv.getModel().get("Author"), "Authors should match");
        assertEquals(expectedDate, mv.getModel().get("date"), "Date should match");
    }
}
