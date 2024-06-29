package ar.com.travel.manager.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.travel.manager.interfaces.path.PathInterface;
import ar.com.travel.manager.model.exception.PathServiceException;
import ar.com.travel.manager.model.request.PathRequest;
import ar.com.travel.manager.model.response.GenericErrorResponse;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/paths")
public class PathController {

	@Autowired
	private PathInterface pathInterface;

	@PostMapping
	public ResponseEntity<?> createPath(@Valid @RequestBody PathRequest request) {
		pathInterface.createPath(request);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/{sourceId}/{destinationId}")
	public ResponseEntity<?> getBestPath(@PathParam(value = "sourceId") Long sourceId,
			@PathParam(value = "destinationId") Long destinationId) {
		return ResponseEntity.ok().body(pathInterface.getBestPath(sourceId, destinationId));
	}

	@ExceptionHandler(PathServiceException.class)
	private ResponseEntity<GenericErrorResponse> handleExComitent(PathServiceException ex) {
		GenericErrorResponse errorResponse = new GenericErrorResponse();

		errorResponse.setPath("/paths");
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setTimestamp(LocalDateTime.now().toString());

		return ResponseEntity.status(ex.getHttpStatus()).body(errorResponse);
	}
}