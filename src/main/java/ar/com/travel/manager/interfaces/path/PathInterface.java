package ar.com.travel.manager.interfaces.path;

import ar.com.travel.manager.model.exception.PathServiceException;
import ar.com.travel.manager.model.request.PathRequest;

public interface PathInterface {
	
	/**
	 * Crea el camino
	 * 
	 * @param request - request del camino
	 * @throws PathServiceException
	 */
	public void createPath(PathRequest request) throws PathServiceException;

}
