package wineRepository;

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
	private static List<Wine> store = new LinkedList<>();

	private CSVReader reader;
	
	public List<String[]> read() throws Exception {
		
		try {
			reader = new CSVReader(new FileReader(
					"C:\\Users\\Alexander\\eclipse-workspace\\riojavino\\src\\main\\resources\\data\\store.csv"));
			LinkedList<String[]> list = (LinkedList<String[]>) reader.readAll();
			
			return list;
		} catch (Exception e) {
			throw new Exception("Error! There is no such file");
		}
	}
	
	public List<Wine> initStore() throws Exception{
		List<String[]> list = read();
		
		return store = convertToWines(list);
	}
	
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
	
	public boolean check(Wine wine) {

		if (store.stream().anyMatch(e -> wine.equals(e))) {
			return true;
		}

		return false;
	}

	public static List<Wine> getStore() {
		return store;
	}
}
