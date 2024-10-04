package com.cooksys.example.dto;

import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CarResponseDto {
	
	private Long id;
	
	private String make;
	
	private List<TireResponseDto> tires;

}
