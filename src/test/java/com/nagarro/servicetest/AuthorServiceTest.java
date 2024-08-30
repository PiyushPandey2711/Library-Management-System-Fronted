package com.nagarro.servicetest;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import com.nagarro.entity.Author;

import com.nagarro.service.AuthorService;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AuthorServiceTest {

    @InjectMocks
    private AuthorService authorService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllAuthors() throws Exception {
    	
    	
        // Mock response data
        JSONArray mockResponseArray = new JSONArray();

        JSONObject author1 = new JSONObject();
        author1.put("authorName", "SK Singh");
        mockResponseArray.add(author1);

        JSONObject author2 = new JSONObject();
        author2.put("authorName", "PS Chopra");
        mockResponseArray.add(author2);

        String mockResponse = mockResponseArray.toJSONString();

        
        when(restTemplate.getForObject("http://localhost:7071/author", String.class))
            .thenReturn(mockResponse);

        
        List<Author> authors = authorService.getAllAuthors();

        // Assertions to verify the results
        assertNotNull(authors);
        assertEquals(2, authors.size());
        assertEquals("SK Singh", authors.get(0).getAuthorName());
        assertEquals("PS Chopra", authors.get(1).getAuthorName());
    }
}

