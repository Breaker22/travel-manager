package ar.com.travel.manager.helper;

import ar.com.travel.manager.model.entity.Station;

public class TestMockHelper {
	
	private TestMockHelper() {		
	}
	
	public static Station mockStation() {
		return new Station(Long.valueOf(2), "Barcelona");
	}

}