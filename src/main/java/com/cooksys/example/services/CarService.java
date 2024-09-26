package com.cooksys.example.services;

import java.util.List;

import com.cooksys.example.dto.CarRequestDto;
import com.cooksys.example.dto.CarResponseDto;
import com.cooksys.example.dto.TireRequestDto;

public interface CarService {

	List<CarResponseDto> getAllCars();

	CarResponseDto createCar(CarRequestDto carRequestDto);

	CarResponseDto replaceTire(Long carId, Long tireId, TireRequestDto tireRequestDto);

	CarResponseDto deleteCar(Long carId);

	CarResponseDto deleteTireFromCar(Long carId, Long tireId);

}
