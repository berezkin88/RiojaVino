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
	private WineService clone = new WineService();
//	private WineService selected;
	private String[] skus;
	private Map<Wine, Boolean> results;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// create clone of existing wine db before update
		clone.setBasket(WineRepository.getStore());
		results = new HashMap<>();
		skus = request.getParameter("items").split(",");

		try {
			checkWithStore();
		} catch (Exception e) {
			// ignore
		}
//		createOrder(skus);

		request.setAttribute("selected", results);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/basket.jsp");
		dispatcher.forward(request, response);
	}
	
//	public void createOrder(String[] skus) {
//		if (!(skus == null) || (skus.length > 0)) {
//			for (int i = 0; i < skus.length; i++) {
//				System.out.println(skus[i] + " found");
//				try {
//					selected.add(Integer.parseInt(skus[i]));
//				} catch (Exception e) {
//				}
//			}
//		}
//	}
	
	public void checkWithStore() throws Exception {
		WineRepository.update();

		for (String item : skus) {
			results.put(clone.findBySKU(Integer.parseInt(item)), WineRepository.checkSKU(Integer.parseInt(item)));
		}
	}
}
