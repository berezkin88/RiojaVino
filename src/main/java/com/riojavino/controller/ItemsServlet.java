package com.riojavino.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.riojavino.entity.Wine;
import com.riojavino.wineRepository.WineRepository;
/**
 * Servlet implementation class ItemsServlet
 * 
 * @author Aleksandr Beryozkin
 */

@WebServlet(name = "ItemsServlet", urlPatterns = "/shop/items")
public class ItemsServlet extends HttpServlet {
	private List<Wine> items = WineRepository.getStore();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("wines", items);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/items.jsp");
		dispatcher.forward(request, response);
	}
}
