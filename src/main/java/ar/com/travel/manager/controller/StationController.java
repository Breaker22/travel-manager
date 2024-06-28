package ar.com.travel.manager.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.travel.manager.interfaces.station.StationInterface;
import ar.com.travel.manager.model.exception.StationtException;
import ar.com.travel.manager.model.request.StationRequest;
import ar.com.travel.manager.model.response.GenericErrorResponse;
import jakarta.validation.Valid;

@RestController("/stations")
public class StationController {
	
	@Autowired
	private StationInterface stationInterface;
	
	@PostMapping
	public ResponseEntity<?> createStation(@Valid @RequestBody StationRequest request) {
		stationInterface.createStation(request);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@ExceptionHandler(StationtException.class)
	private ResponseEntity<GenericErrorResponse> handleExComitent(StationtException ex) {
		GenericErrorResponse errorResponse = new GenericErrorResponse();
		
		errorResponse.setPath("/stations");
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setTimestamp(LocalDateTime.now().toString());
		
		return ResponseEntity.status(ex.getHttpStatus()).body(errorResponse);
	}
}