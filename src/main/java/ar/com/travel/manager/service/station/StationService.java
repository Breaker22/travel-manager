package ar.com.travel.manager.service.station;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ar.com.travel.manager.interfaces.station.StationInterface;
import ar.com.travel.manager.model.entity.Station;
import ar.com.travel.manager.model.exception.StationtException;
import ar.com.travel.manager.model.repository.StationRepository;
import ar.com.travel.manager.model.request.StationRequest;

@Service
public class StationService implements StationInterface {

	@Autowired
	private StationRepository stationRepo;

	@Override
	public void createStation(StationRequest request) {
		Optional<Station> oldStation = stationRepo.findById(request.getStationId());

		if (oldStation.isPresent()) {
			throw new StationtException(HttpStatus.CONFLICT, "El id de la estacion ya existe!");
		}

		stationRepo.save(new Station(request.getStationId(), request.getName()));
	}
}