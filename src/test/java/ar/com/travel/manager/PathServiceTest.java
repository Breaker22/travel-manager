package ar.com.travel.manager;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.travel.manager.helper.TestMockHelper;
import ar.com.travel.manager.model.exception.PathServiceException;
import ar.com.travel.manager.model.repository.PathRepository;
import ar.com.travel.manager.model.repository.StationRepository;
import ar.com.travel.manager.model.request.PathRequest;
import ar.com.travel.manager.service.path.PathService;

@SpringBootTest
public class PathServiceTest {

	@InjectMocks
	private PathService pathService;

	@Mock
	private StationRepository stationRepo;

	@Mock
	private PathRepository pathRepository;

	@Test
	public void testCreatePath_shouldNotThrowEx() {
		PathRequest request = new PathRequest();

		request.setPathId(Long.valueOf(2));
		request.setCost(Double.valueOf("23"));
		request.setSourceId(Long.valueOf(3));
		request.setDestinationId(Long.valueOf(2));

		Mockito.when(stationRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(TestMockHelper.mockStation()));

		pathService.createPath(request);

		Assertions.assertDoesNotThrow(() -> PathServiceException.class);
	}

	@Test
	public void testCreatePath_shouldThrowEx() {
		PathRequest request = new PathRequest();

		request.setPathId(Long.valueOf(2));
		request.setCost(Double.valueOf("23"));
		request.setSourceId(Long.valueOf(3));
		request.setDestinationId(Long.valueOf(2));

		Assertions.assertThrows(PathServiceException.class, () -> pathService.createPath(request));
	}
}