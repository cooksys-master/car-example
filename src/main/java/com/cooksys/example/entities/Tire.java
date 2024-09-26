package com.cooksys.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Tire {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String manufacturer;
	
	@ManyToOne
	@JoinColumn
	private Car car;

}
