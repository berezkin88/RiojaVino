package com.riojavino.controller;


import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.riojavino.wineRepository.WineRepository;

/**
 * Servlet implementation class HomeServlet
 * 
 * @author Aleksandr Beryozkin
 */

@WebServlet(name = "HomeServlet", urlPatterns = "")
public class HomeServlet extends HttpServlet {
	private WineRepository wr;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		wr = new WineRepository(System.getProperty("catalina.home") + File.separator + "data"
				+ File.separator + "store.csv");
		initAndUpdate();

		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/home.html");
		dispatcher.forward(request, response);
	}
	
	public void initAndUpdate() {

		try {
			wr.initStore();
			
			Thread t = new Thread(() -> {
				try {
					System.out.println("First sleep");
					// 5 minutes
					Thread.sleep(5*60*1000);
					
					while(true) {
						WineRepository.update();
						System.out.println("Store updated");
						Thread.sleep(5*60*1000);
					}
				} catch (Exception e) {
					System.out.println("Error occured in homeServlet internal thread");
				}
			});
			t.setDaemon(true);
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}