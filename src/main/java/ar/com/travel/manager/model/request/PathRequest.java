package ar.com.travel.manager.model.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;


@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@Setter
public class PathRequest {
	
	private Long pathId;
	
	private Double cost;
	
	private Long sourceId;
	
	private Long destinationId;

}
