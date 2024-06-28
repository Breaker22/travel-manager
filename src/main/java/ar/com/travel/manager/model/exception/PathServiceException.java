package ar.com.travel.manager.model.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class PathServiceException extends RuntimeException {

	private static final long serialVersionUID = -4623677244135521835L;
	
	private HttpStatus httpStatus;

	public PathServiceException(HttpStatus httpStatus, String message) {
		super(message);

		this.httpStatus = httpStatus;
	}
}