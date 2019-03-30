package com.riojavino.controller;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.riojavino.entity.Wine;
import com.riojavino.entity.wineExceptions.WineNotFoundException;
import com.riojavino.service.WineService;
import com.riojavino.wineRepository.WineRepository;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Servlet implementation class buyServlet
 * 
 * @author Aleksandr Beryozkin
 */

@WebServlet(name = "BuyServlet", urlPatterns = "/buyService", asyncSupported = true)
public class BuyServlet extends HttpServlet {
	private WineService order;
	private String[] skus;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		order = new WineService();
		skus = request.getParameter("items").split(",");

		try {
			checkout();
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/success.html");
			response.setStatus(201);
			dispatcher.forward(request, response);
		} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
			System.out.println("In csv catch");
			failureAction(request, response);
		} catch (WineNotFoundException e) {
			System.out.println("In WNF catch");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/failure.html");
			response.setStatus(400);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println("In other catch");
			failureAction(request, response);
		}
	}

	private void failureAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/failure.html");
		response.setStatus(500);
		dispatcher.forward(request, response);
	}

	// writing wines to scv file as an order
	private void checkout() throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, WineNotFoundException, Exception {
		System.out.println("In checkout method");
		
		Writer writer = Files.newBufferedWriter(Paths
				.get(System.getProperty("catalina.home") + File.separator + "data" + File.separator + "order-" + System.currentTimeMillis() + ".csv"));
		System.out.println("Writer started");
		StatefulBeanToCsv<Wine> beanToCsv = new StatefulBeanToCsvBuilder<Wine>(writer).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
				.build();
		
		System.out.println("CSV builder completed");
		
		validateWines();
		
		beanToCsv.write(order.getBasket());
		System.out.println("CSV completed");
		
		writer.close();
	}
	
	public void validateWines() throws Exception {
		WineRepository.update();

		if (!(skus == null) || (skus.length > 0)) {
			for (String item : skus) {
				if (WineRepository.checkSKU(Integer.parseInt(item))) {
					// adding wine to order
					order.getBasket().add(WineRepository.findBySKU(Integer.parseInt(item)));
				}
			}
		}
		
		if(order.getBasket().isEmpty()) {
			throw new WineNotFoundException("The Wine is out of stock");
		}
	}
}
