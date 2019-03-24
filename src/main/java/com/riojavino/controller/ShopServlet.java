package com.riojavino.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ItemsServlet
 * 
 * @author Aleksandr Beryozkin
 */

@WebServlet(name = "ShopServlet", urlPatterns = "/riojavino/shop")
public class ShopServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
	
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/shop.html");
		dispatcher.forward(request, response);
	}
}
