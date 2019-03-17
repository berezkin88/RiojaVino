package com.riojavino.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wineRepository.WineRepository;

import com.riojavino.entity.Wine;

import wineRepository.WineRepository;

/**
 * Servlet implementation class ItemsServlet
 * 
 * @author Aleksandr Beryozkin
 */

@WebServlet(name = "ItemsServlet", urlPatterns = "/shop/items")
public class ItemsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected List<Wine> items = WineRepository.getStore();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("wines", items);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/./items.jsp");
		dispatcher.forward(request, response);
	}
}
