package com.cooksys.example.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.example.dto.CarRequestDto;
import com.cooksys.example.dto.CarResponseDto;
import com.cooksys.example.dto.TireRequestDto;
import com.cooksys.example.entities.Car;
import com.cooksys.example.entities.Tire;
import com.cooksys.example.services.CarService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {
	
	private final CarService carService;
	
	@GetMapping
	public List<CarResponseDto> getAllCars() {
		return carService.getAllCars();
	}
	
	@PostMapping
	public CarResponseDto createCar(@RequestBody CarRequestDto doesNotMatter) {
		System.out.println("Make of the car: " + doesNotMatter.getMake());
		System.out.println("Model of the car: " + doesNotMatter.getModel());
		for (TireRequestDto whatEverIWant : doesNotMatter.getTires()) {
			System.out.println("Tire manufacturer: " + whatEverIWant.getManufacturer());
		}
		return carService.createCar(doesNotMatter);
	}
	
	@PatchMapping("/{id}/tire/{tireId}")
	public CarResponseDto replaceTire(@RequestBody TireRequestDto tireRequestDto, @PathVariable(name = "id") Long carId, @PathVariable Long tireId) {
		return carService.replaceTire(carId, tireId, tireRequestDto);
	}
	
	@DeleteMapping("/{carId}")
	public CarResponseDto deleteCar(@PathVariable Long carId) {
		return carService.deleteCar(carId);
	}
	
	@DeleteMapping("/{carId}/tire/{tireId}")
	public CarResponseDto deleteTireFromCar(@PathVariable Long carId, @PathVariable Long tireId) {
		return carService.deleteTireFromCar(carId, tireId);
	}
}
