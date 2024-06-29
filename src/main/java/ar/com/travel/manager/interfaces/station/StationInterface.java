package ar.com.travel.manager.interfaces.station;

import ar.com.travel.manager.model.request.StationRequest;

public interface StationInterface {

	/**
	 * Crea una estacion
	 * 
	 * @param request - request de la estacion
	 */
	public void createStation(StationRequest request);

}
