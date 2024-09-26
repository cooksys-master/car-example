package com.cooksys.example.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CarRequestDto {
	
	private String make;

	private String model;

	private List<TireRequestDto> tires;

}
