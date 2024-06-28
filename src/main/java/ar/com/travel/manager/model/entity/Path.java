package ar.com.travel.manager.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Path implements Serializable {

	private static final long serialVersionUID = 2575355952336533078L;
	
	@Id
	private Long pathId;
	
	@Column(nullable= false)
	private Double cost;
	
	@Column(nullable= false)
	private Long sourceId;
	
	@Column(nullable= false)
	private Long destinationId;

}
