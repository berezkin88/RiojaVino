package wineService;

import static org.mockito.Mockito.mock;

import org.junit.Test;
import static org.mockito.Mockito.*;

import com.riojavino.service.WineService;

import wineRepository.WineRepository;

public class WineServiceTest {
	
	@Test(expected = Exception.class)
	public void shouldThrowException() throws Exception {
		int sku = 1;
		WineRepository wr = mock(WineRepository.class);
		WineService ws = mock(WineService.class);
		
		doThrow(new Exception()).when(ws).add(sku);
		
		ws.add(sku);
		
		verify(ws).add(sku);
	}

}
