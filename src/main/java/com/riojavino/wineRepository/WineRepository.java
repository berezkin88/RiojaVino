package com.riojavino.wineRepository;

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
	private static String file;
	
	public WineRepository(String file) {
		WineRepository.file = file;
	}

	//read from csv
	public static List<String[]> read() {
		System.out.println("Start reading");
		
		try {
			CSVReader reader = new CSVReader(new FileReader(file));
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
	public static void update() throws Exception{
		store = convertToWines(read());
	}
	
	public static LinkedList<Wine> convertToWines(List<String[]> list) {
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

		return store.stream().anyMatch(e -> e.getSku() == sku);

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
