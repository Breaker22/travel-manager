package ar.com.travel.manager.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericErrorResponse {
	
	private String timestamp;
	
	private String path;
	
	private String message;

}
