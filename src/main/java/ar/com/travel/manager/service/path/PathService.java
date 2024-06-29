package ar.com.travel.manager.service.path;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.Multigraph;
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
import ar.com.travel.manager.model.response.path.PathResponse;

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
			throw new PathServiceException(HttpStatus.NOT_FOUND, "La estacion de origen no existe!");
		}

		if (destinationStation.isEmpty()) {
			throw new PathServiceException(HttpStatus.NOT_FOUND, "La estacion de destino no existe!");
		}

		path.setPathId(request.getPathId());
		path.setCost(request.getCost());
		path.setSourceId(request.getSourceId());
		path.setDestinationId(request.getDestinationId());

		pathRepository.save(path);
	}

	@Override
	public PathResponse getBestPath(Long sourceId, Long destinationId) {
		Optional<Station> sourceStation = stationRepo.findById(sourceId);
		Optional<Station> destinationStation = stationRepo.findById(destinationId);

		if (sourceStation.isEmpty()) {
			throw new PathServiceException(HttpStatus.NOT_FOUND, "La estacion de origen no existe!");
		}

		if (destinationStation.isEmpty()) {
			throw new PathServiceException(HttpStatus.NOT_FOUND, "La estacion de destino no existe!");
		}

		return shortestPath(sourceId, destinationId);
	}

	/**
	 * Calcula cual es camino mas corto y retorna la respuesta
	 * 
	 * @param sourceId      - id de origen
	 * @param destinationId - id destino
	 * @return el objeto que contiene el mejor camino con el precio
	 */
	private PathResponse shortestPath(Long sourceId, Long destinationId) {
		PathResponse response = new PathResponse();

		DijkstraShortestPath<Long, Long> shortestPath = new DijkstraShortestPath(buildGraph());
		GraphPath<Long, Long> graphShortestPath = shortestPath.getPath(sourceId, destinationId);

		response.setPath(graphShortestPath.getVertexList());
		response.setCost(graphShortestPath.getWeight());

		return response;
	}

	/**
	 * Arma el grafo de las rutas
	 * 
	 * @return el grafo con todas las rutas
	 */
	private Multigraph<Long, DefaultEdge> buildGraph() {
		List<Path> allRoutes = pathRepository.findAll();
		Multigraph<Long, DefaultEdge> multiGraph = new Multigraph<>(DefaultWeightedEdge.class);

		for (Path path : allRoutes) {
			multiGraph.addVertex(path.getSourceId());
			multiGraph.addVertex(path.getDestinationId());

			DefaultEdge edge1 = multiGraph.addEdge(path.getSourceId(), path.getDestinationId());
			DefaultEdge edge2 = multiGraph.addEdge(path.getDestinationId(), path.getSourceId());

			multiGraph.setEdgeWeight(edge1, path.getCost());
			multiGraph.setEdgeWeight(edge2, path.getCost());
		}

		return multiGraph;
	}
}