package ar.com.travel.manager.model.response.path;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PathResponse {

	private List<Long> path;

	private Double cost;
}
