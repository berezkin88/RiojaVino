package com.riojavino.service;

import java.util.LinkedList;
import java.util.List;

import com.riojavino.entity.Wine;
import com.riojavino.wineRepository.WineRepository;

/**
 * 
 * @author Aleksandr Beryozkin
 *
 * This class purpose is to add wine to basket
 */

public class WineService {
	
	private List<Wine> basket;
	
	public WineService() {
		basket = new LinkedList<>();
	}

	public void add(int sku) throws Exception{
		try {
			basket.add(WineRepository.findBySKU(sku));
		} catch (Exception e) {
			System.out.println("doesn't found");
			throw new Exception("Oops( thw wine is out of stock");
		}
	}

	public  Wine findBySKU(int sku) throws Exception{

		for (Wine item : basket) {
			if(item.getSku() == sku)
				return item;
		}

		throw new Exception();
	}


	public List<Wine> getBasket() {
		return basket;
	}

	public void setBasket(List<Wine> basket) {
		this.basket = basket;
	}
	
	
}
