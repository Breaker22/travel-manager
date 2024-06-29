package ar.com.travel.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.travel.manager.helper.TestMockHelper;
import ar.com.travel.manager.model.entity.Path;
import ar.com.travel.manager.model.exception.PathServiceException;
import ar.com.travel.manager.model.repository.PathRepository;
import ar.com.travel.manager.model.repository.StationRepository;
import ar.com.travel.manager.model.request.PathRequest;
import ar.com.travel.manager.model.response.path.PathResponse;
import ar.com.travel.manager.service.path.PathService;

@SpringBootTest
class PathServiceTest {

	@InjectMocks
	private PathService pathService;

	@Mock
	private StationRepository stationRepo;

	@Mock
	private PathRepository pathRepository;

	@Test
	void testCreatePath_shouldNotThrowEx() {
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
	void testCreatePath_shouldThrowEx() {
		PathRequest request = new PathRequest();

		request.setPathId(Long.valueOf(2));
		request.setCost(Double.valueOf("23"));
		request.setSourceId(Long.valueOf(3));
		request.setDestinationId(Long.valueOf(2));

		Assertions.assertThrows(PathServiceException.class, () -> pathService.createPath(request));
	}

	@Test
	void testCalulatePath_shouldCalulateShortPath() {
		Mockito.when(stationRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(TestMockHelper.mockStation()));

		Mockito.when(pathRepository.findAll()).thenReturn(mockListPaths());

		PathResponse response = pathService.getBestPath(Long.valueOf(12), Long.valueOf(11));

		Assertions.assertEquals(Double.valueOf(130), response.getCost());
	}

	private List<Path> mockListPaths() {
		ArrayList<Path> listPaths = new ArrayList<>();
		Path path = new Path();

		path.setPathId(Long.valueOf(1));
		path.setCost(Double.valueOf(50));
		path.setSourceId(Long.valueOf(10));
		path.setDestinationId(Long.valueOf(11));

		listPaths.add(path);

		path = new Path();

		path.setPathId(Long.valueOf(2));
		path.setCost(Double.valueOf(100));
		path.setSourceId(Long.valueOf(10));
		path.setDestinationId(Long.valueOf(12));

		listPaths.add(path);

		path.setPathId(Long.valueOf(3));
		path.setCost(Double.valueOf(60));
		path.setSourceId(Long.valueOf(10));
		path.setDestinationId(Long.valueOf(13));

		listPaths.add(path);

		path = new Path();

		path.setPathId(Long.valueOf(4));
		path.setCost(Double.valueOf(20));
		path.setSourceId(Long.valueOf(13));
		path.setDestinationId(Long.valueOf(12));

		listPaths.add(path);

		return listPaths;
	}
}