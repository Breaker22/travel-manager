package ar.com.travel.manager.interfaces.path;

import ar.com.travel.manager.model.exception.PathServiceException;
import ar.com.travel.manager.model.request.PathRequest;
import ar.com.travel.manager.model.response.path.PathResponse;

public interface PathInterface {

	/**
	 * Crea el camino
	 * 
	 * @param request - request del camino
	 * @throws PathServiceException
	 */
	public void createPath(PathRequest request);

	/**
	 * Busca el mejor precio de un camino
	 * 
	 * @param sourceId      - id de origen
	 * @param destinationId - id destino
	 * @return response del mejor camino
	 */
	public PathResponse getBestPath(Long sourceId, Long destinationId);

}
