package ar.com.travel.manager.service.path;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ar.com.travel.manager.interfaces.path.PathInterface;
import ar.com.travel.manager.model.entity.Path;
import ar.com.travel.manager.model.entity.Station;
import ar.com.travel.manager.model.exception.PathServiceException;
import ar.com.travel.manager.model.repository.PathRepository;
import ar.com.travel.manager.model.repository.StationRepository;
import ar.com.travel.manager.model.request.PathRequest;

@Service
public class PathService implements PathInterface {

	@Autowired
	private PathRepository pathRepository;

	@Autowired
	private StationRepository stationRepo;

	@Override
	public void createPath(PathRequest request) {
		Path path = new Path();
		Optional<Station> sourceStation = stationRepo.findById(request.getSourceId());
		Optional<Station> destinationStation = stationRepo.findById(request.getDestinationId());

		if (sourceStation.isEmpty()) {
			throw new PathServiceException(HttpStatus.BAD_REQUEST, "La estacion de origen no existe!");
		}

		if (destinationStation.isEmpty()) {
			throw new PathServiceException(HttpStatus.BAD_REQUEST, "La estacion de destino no existe!");
		}

		path.setPathId(request.getPathId());
		path.setCost(request.getCost());
		path.setSourceId(request.getSourceId());
		path.setDestinationId(request.getDestinationId());

		pathRepository.save(path);
	}
}