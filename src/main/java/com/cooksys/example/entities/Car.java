package com.cooksys.example.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Car {

	@Id
	@GeneratedValue
	private Long id;

	private String make;

	private String model;

	@OneToMany(mappedBy = "car")
	private List<Tire> tires;

}
