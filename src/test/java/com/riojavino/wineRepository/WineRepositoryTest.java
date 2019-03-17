package com.riojavino.wineRepository;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;
import org.junit.Test;

import com.riojavino.entity.Wine;

import wineRepository.WineRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 
 * @author Aleksandr Beryozkin
 *
 */

public class WineRepositoryTest {
	
	@Test
	public void shouldReturnTrue() {
		Wine wine = new Wine("Lan", 1111, 9.99);
		LinkedList<Wine> testStore = new LinkedList<>();
		
		testStore.add(wine);
		
		assertEquals(1, testStore.size());
		assertTrue(testStore.stream().anyMatch(e -> wine.equals(e)));
	}
	
	@Test
	public void shouldReturnList() throws Exception{
		List<String[]> testStore = mock(List.class);
		WineRepository wr = mock(WineRepository.class);
		
		testStore.addAll(wr.read());
		
		verify(testStore).addAll(wr.read());
		
		assertEquals(true, !testStore.isEmpty());
	}
	
	@Test
	public void shouldReturnWines() throws Exception{
		List<String[]> testStore = mock(List.class);
		LinkedList<Wine> wines = mock(LinkedList.class);
		WineRepository wr = mock(WineRepository.class);
		
		testStore.addAll(wr.read());
		wines.addAll(wr.convertToWines(testStore));
		
		verify(testStore).addAll(wr.read());
		verify(wines).addAll(wr.convertToWines(testStore));
		
		assertEquals(true, !wines.isEmpty());
	}
	
	@Test
	public void shouldFindWineBySKU() {
		Wine wine = new Wine("Lan", 1111, 9.99);
		List<Wine> testStore = new LinkedList<>();
		WineRepository wr = new WineRepository();
		
		testStore.add(wine);
		wr.setStore(testStore);
				
		assertEquals(1, wr.getStore().size());
		assertTrue(wr.checkSKU(wine.getSku()));
	}
	
	@Test
	public void shouldThrowException() {
		Wine wine = new Wine("Lan", 1111, 9.99);
		List<Wine> testStore = new LinkedList<>();
		WineRepository wr = new WineRepository();
		
		testStore.add(wine);
		wr.setStore(testStore);
		
		assertEquals(1, wr.getStore().size());
		assertFalse(wr.checkSKU(2));
	}
}
