package com.riojavino.controller;

import static org.junit.Assert.assertEquals;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

public class HomeServletTest extends Mockito{
	
	@Test
	@Ignore
	public void shouldReturnHomePage() throws Exception{
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        HomeServlet servlet = mock(HomeServlet.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        
        when(request.getRequestDispatcher("home.html")).thenReturn(dispatcher);
        
        servlet.doGet(request, response);
        
        verify(dispatcher).forward(request, response);
        
        assertEquals(200, response.getStatus());
	}

}
