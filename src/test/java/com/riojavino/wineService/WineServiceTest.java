package wineService;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import com.riojavino.service.WineService;
import com.riojavino.wineRepository.WineRepository;

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
