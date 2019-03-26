package com.riojavino.wineRepository;

import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List; 

import com.opencsv.CSVReader;
import com.riojavino.entity.Wine;

/**
 * 
 * @author Aleksandr Beryozkin
 *
 * This class is tending after wine stock - reading, updating, checking
 */

public class WineRepository {
	
	private static List<Wine> store;
	private CSVReader reader;
	private String file;
	
	public WineRepository(String file) {
		this.file = file;
	}

	//read from csv
	public List<String[]> read() throws Exception {
//		File file =
//				new File("C:\\Users\\Alexander\\eclipse-workspace\\riojavino\\src\\main\\webapp\\WEB-INF\\data\\store.csv");
		System.out.println("Start reading");
		
		try {
			reader = new CSVReader(new FileReader(file));
			LinkedList<String[]> list = (LinkedList<String[]>) reader.readAll();
			
			System.out.println("Reading complete");
			return list;
		} catch (Exception e) {
			System.out.println("Error in wine repository!");
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Wine> initStore() throws Exception{
		List<String[]> list = read();
		
		return store = convertToWines(list);
	}
	
	//update store
	public void update() throws Exception{			
		store = convertToWines(read());
	}
	
	public LinkedList<Wine> convertToWines(List<String[]> list) {
		LinkedList<Wine> wines = new LinkedList<>();
		
		if (list.isEmpty()) 
			return wines;
		
		// adding wines to list
		for (int i = 1; i < list.size(); i++) {
			wines.add(new Wine(list.get(i)[0], Integer.parseInt(list.get(i)[1]), Double.parseDouble(list.get(i)[2])));
		}
		return wines;
	}
	
	public static boolean checkSKU(int sku) {

		if (store.stream().anyMatch(e -> e.getSku() == sku)) {
			return true;
		}

		return false;
	}
	
	public static Wine findBySKU(int sku) throws Exception{

		for (Wine item : store) {
			if(item.getSku() == sku)
				return item;
		}
		
		throw new Exception();
	}

	public static List<Wine> getStore() {
		return store;
	}

	public static void setStore(List<Wine> store) {
		WineRepository.store = store;
	}
}
