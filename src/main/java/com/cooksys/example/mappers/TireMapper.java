package com.cooksys.example.mappers;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;

import com.cooksys.example.dto.TireRequestDto;
import com.cooksys.example.dto.TireResponseDto;
import com.cooksys.example.entities.Tire;

@Mapper(componentModel = "spring")
public interface TireMapper {
	
	Tire requestDtoToEntity(TireRequestDto tireRequestDto);
	
	List<Tire> requestDtosToEntities(List<TireRequestDto> tireRequestDtos);
	
	TireResponseDto entityToDto(Tire tire);
	
	List<TireResponseDto> entitiesToDtos(List<Tire> tires);

}
