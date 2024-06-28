package ar.com.travel.manager.model.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class StationtException extends RuntimeException {

	private static final long serialVersionUID = 5485458687796217082L;
	
	private HttpStatus httpStatus;
	
	public StationtException(HttpStatus httpStatus, String message) {
		super(message);
		
		this.httpStatus = httpStatus;
	}

}
