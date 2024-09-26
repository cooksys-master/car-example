package com.cooksys.example.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.example.dto.CarRequestDto;
import com.cooksys.example.dto.CarResponseDto;
import com.cooksys.example.entities.Car;

@Mapper(componentModel = "spring", uses = TireMapper.class)
public interface CarMapper {
	
	Car requestDtoToEntity(CarRequestDto carRequestDto);
	
	CarResponseDto entityToDto(Car car);
	
	List<CarResponseDto> entitiesToDtos(List<Car> car);

}
