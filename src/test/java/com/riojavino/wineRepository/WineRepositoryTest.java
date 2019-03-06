package com.riojavino.wineRepository;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;
import org.junit.Test;

import com.riojavino.entity.Wine;

import wineRepository.WineRepository;

import static org.junit.Assert.assertEquals;

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
		assertEquals(true, testStore.stream().anyMatch(e -> wine.equals(e)));
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
}
