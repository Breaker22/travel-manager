package ar.com.travel.manager.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Station implements Serializable {
	
	private static final long serialVersionUID = -1727848887847162068L;

	@Id
	@Column(nullable= false)
	private Long stationId;
	
	@Column(nullable= false)
	private String name;

}
