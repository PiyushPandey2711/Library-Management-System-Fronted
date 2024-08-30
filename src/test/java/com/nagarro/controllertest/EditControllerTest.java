package com.nagarro.controllertest;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.controller.EditController;
import com.nagarro.entity.Author;
import com.nagarro.entity.Book;
import com.nagarro.service.AuthorService;
import com.nagarro.service.BookService;

public class EditControllerTest {

    @InjectMocks
    private EditController editController;

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

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testEditBook() throws Exception {
        // Mock request parameters
        when(request.getParameter("bookCode")).thenReturn("1");
        when(request.getParameter("bookName")).thenReturn("Book 1");
        when(request.getParameter("author")).thenReturn("Author 1");
        when(request.getParameter("dataAddedOn")).thenReturn("2023-06-20");

        
        List<Author> mockAuthors = Arrays.asList(new Author("Author 1"), new Author("Author 2"));
        when(authorService.getAllAuthors()).thenReturn(mockAuthors);

        // Call the method to test
        ModelAndView mv = editController.editBook(request, response);

        
        verify(book).setBookCode(1L);
        verify(book).setBookName("Book 1");
        verify(book).setAuthor("Author 1");
        verify(book).setDataAddedOn("2023-06-20");

        // Assertions to verify the results
        assertNotNull(mv);
        assertEquals("editBook", mv.getViewName());
        assertNotNull(mv.getModel().get("book"));
        assertNotNull(mv.getModel().get("Author"));
        assertEquals(mockAuthors, mv.getModel().get("Author"));
    }
}
