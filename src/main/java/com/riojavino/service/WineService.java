package com.riojavino.service;

import java.util.LinkedList;
import java.util.List;

import com.riojavino.entity.Wine;

import wineRepository.WineRepository;

/**
 * 
 * @author Aleksandr Beryozkin
 *
 * This class purpose is to add wine to basket
 */

public class WineService {
	
	private List<Wine> basket = new LinkedList<>();
	private WineRepository wineRepository;
	
	public void add(Wine wine) throws Exception{
		
		if (wineRepository.check(wine)) {
			basket.add(wine);
		} else {
			throw new Exception("Sorry( The wine is already out of stock");
		}
	}
}
