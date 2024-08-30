package com.nagarro.controllertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.controller.Logout;

public class LogoutControllerTest {

    @InjectMocks
    private Logout logoutController;

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
    public void testLogout() {
        // Call the method to test
        ModelAndView mv = logoutController.logout(request, response);

        // Verify that the session was invalidated
        verify(session).invalidate();

        // Assertions to verify the results
        assertEquals("index", mv.getViewName(), "View name should be 'index'");
    }
}

