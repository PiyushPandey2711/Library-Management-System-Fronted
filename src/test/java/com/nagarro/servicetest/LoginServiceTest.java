package com.nagarro.servicetest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import com.nagarro.service.LoginService;

public class LoginServiceTest {

    @InjectMocks
    private LoginService loginService;

    @Mock
    //@Autowired
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testValidateUserSuccess() throws Exception {
        // Mock response data
    	String mockResponse = "{\"username\":\"piyush\",\"password\":\"piyush\"}";

        
        ResponseEntity<String> mockResponseEntity = new ResponseEntity<>(mockResponse, HttpStatus.OK);

       
        when(restTemplate.getForEntity("http://localhost:7071/user/piyush", String.class)).thenReturn(mockResponseEntity);

        // Call the method to test
        boolean isValidUser = loginService.validateUser("piyush", "piyush");

        
        assertTrue(isValidUser);
    }

   @Test
    public void testValidateUserFailure() throws Exception {
        // Mock response data
        String mockResponse = "{\"username\":\"piyush\",\"password\":\"pandey\"}";
        
        ResponseEntity<String> mockResponseEntity = new ResponseEntity<>(mockResponse, HttpStatus.OK);

        
        when(restTemplate.getForEntity("http://localhost:7071/user/piyush", String.class)).thenReturn(mockResponseEntity);

        
        boolean isValidUser = loginService.validateUser("piyush", "wrongpassword");

        // Assertions to verify the results
        assertFalse(isValidUser);
    }
}
