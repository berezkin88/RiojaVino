package com.riojavino.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.riojavino.entity.Wine;
import com.riojavino.service.WineService;

/**
 * Servlet implementation class ItemsServlet
 * 
 * @author Aleksandr Beryozkin
 */

@WebServlet(name = "ItemsServlet", urlPatterns = "/shop/items")
public class ItemsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private WineService wineService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath()).append(" This is items");
	}
}
