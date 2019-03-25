package com.riojavino.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.riojavino.entity.Wine;
import com.riojavino.service.WineService;
import com.riojavino.wineRepository.WineRepository;

/**
 * Servlet implementation class BasketServlet
 * 
 * @author Aleksandr Beryozkin
 */

@WebServlet(name = "BasketServlet", urlPatterns = "/shop/basket")
public class BasketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WineService selected;
	private Map<Wine, Boolean> results;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		selected = new WineService();
		results = new HashMap<>();
		String[] skus = request.getParameter("items").split(",");
		
		createOrder(skus);
		checkWithStore();
		
		request.setAttribute("selected", results);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/basket.jsp");
		dispatcher.forward(request, response);
	}
	
	public void createOrder(String[] skus) {
		if (!(skus == null) || (skus.length > 0)) {
			for (int i = 0; i < skus.length; i++) {
				System.out.println(skus[i] + "found");
				try {
					selected.add(Integer.parseInt(skus[i]));
				} catch (Exception e) {
					continue;
				}
			}
		}
	}
	
	public void checkWithStore() {
		for (Wine item : selected.getBasket()) {
			results.put(item, WineRepository.checkSKU(item.getSku()));
		}
	}
}
