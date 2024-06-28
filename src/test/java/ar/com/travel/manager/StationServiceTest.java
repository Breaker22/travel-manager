package ar.com.travel.manager;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.travel.manager.model.entity.Station;
import ar.com.travel.manager.model.exception.StationtException;
import ar.com.travel.manager.model.repository.StationRepository;
import ar.com.travel.manager.model.request.StationRequest;
import ar.com.travel.manager.service.station.StationService;

@SpringBootTest
class StationServiceTest {

	@InjectMocks
	private StationService stationService;

	@Mock
	private StationRepository stationRepo;

	@Test
	void testCreateStation_shouldNotThrowEx() {
		StationRequest request = new StationRequest();

		request.setName("Roma");
		request.setStationId(Long.valueOf(2));

		stationService.createStation(request);

		Assertions.assertDoesNotThrow(() -> StationtException.class);
	}
	
	void testCreateStation_shouldThrowEx() {
		StationRequest request = new StationRequest();

		request.setName("Venecia");
		request.setStationId(Long.valueOf(2));

		stationService.createStation(request);
		
		Mockito.when(stationRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(mockStation()));

		Assertions.assertThrows(StationtException.class, () -> stationService.createStation(request));
	}

	private Station mockStation() {
		return new Station(Long.valueOf(2), "Barcelona");
	}
}