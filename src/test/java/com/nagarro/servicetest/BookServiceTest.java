package com.nagarro.servicetest;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.nagarro.service.BookService;
import com.nagarro.entity.Book;

public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBooks() {
        // Mock response data
        Book[] mockBooks = {
            new Book(1, "Book 1", "Author 1", "2023-06-01"),
            new Book(2, "Book 2", "Author 2", "2023-06-02")
        };

        // Mock RestTemplate's response
        when(restTemplate.getForEntity("http://localhost:7071/books", Book[].class))
            .thenReturn(new ResponseEntity<>(mockBooks, HttpStatus.OK));

        // Call the method to test
        List<Book> books = bookService.getAllBooks();

        // Assertions to verify the results
        assertNotNull(books);
        assertEquals(2, books.size());
        assertEquals("Book 1", books.get(0).getBookName());
    }

    @Test
    public void testSaveBook() {
        // Create a sample book
        Book book = new Book(1, "Book 1", "Author 1", "2023-06-01");

       
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Book> request = new HttpEntity<>(book, headers);

        
        when(restTemplate.postForEntity("http://localhost:7071/books", request, String.class))
            .thenReturn(new ResponseEntity<>(HttpStatus.OK));

        
        bookService.saveBook(book);

       
        verify(restTemplate, times(1)).postForEntity("http://localhost:7071/books", request, String.class);
    }

    @Test
    public void testDeleteBook() {
        long bookCode = 1L;

        // Call the method to test
        bookService.deleteBook(bookCode);

        
        verify(restTemplate, times(1)).delete("http://localhost:7071/books/" + bookCode);
    }
}

