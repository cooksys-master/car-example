package com.cooksys.example.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.cooksys.example.dto.CarRequestDto;
import com.cooksys.example.dto.CarResponseDto;
import com.cooksys.example.dto.TireRequestDto;
import com.cooksys.example.entities.Car;
import com.cooksys.example.entities.Tire;
import com.cooksys.example.mappers.CarMapper;
import com.cooksys.example.repositories.CarRepository;
import com.cooksys.example.repositories.TireRepository;
import com.cooksys.example.services.CarService;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
	
	private final CarMapper carMapper;
	private final CarRepository carRepository;
	private final TireRepository tireRepository;
	
	@Override
	public List<CarResponseDto> getAllCars() {
		return carMapper.entitiesToDtos(carRepository.findAll());
	}

	@Override
	public CarResponseDto createCar(CarRequestDto carRequestDto) {
		Car carToSave = carMapper.requestDtoToEntity(carRequestDto);
		
		carRepository.save(carToSave);
		
		for (Tire t : carToSave.getTires()) {
			t.setCar(carToSave);
			tireRepository.saveAndFlush(t);
		}
		
		return carMapper.entityToDto(carToSave);
	}

	@Override
	public CarResponseDto replaceTire(Long carId, Long tireId, TireRequestDto tireRequestDto) {
		Optional<Car> optionalCar = carRepository.findById(carId);
		if (optionalCar.isPresent()) {
			Optional<Tire> optionalTire = tireRepository.findById(tireId);
			if (optionalTire.isPresent()) {
				Car carInDB = optionalCar.get();
				Tire tireInDB = optionalTire.get();
				if (carInDB.getTires().contains(tireInDB)) {
					tireInDB.setManufacturer(tireRequestDto.getManufacturer());
					tireRepository.save(tireInDB);
					return carMapper.entityToDto(carInDB);
				}
			}
		}
		return null;
	}

	@Override
	public CarResponseDto deleteCar(Long carId) {
		Optional<Car> optionalCar = carRepository.findById(carId);
		if (optionalCar.isPresent()) {
			Car carInDB = optionalCar.get();
			for (Tire tire : carInDB.getTires()) {
				tireRepository.delete(tire);
			}
			carRepository.delete(carInDB);
			return carMapper.entityToDto(carInDB);
		}
		return null; // error handling
	}

	@Override
	public CarResponseDto deleteTireFromCar(Long carId, Long tireId) {
		Optional<Car> optionalCar = carRepository.findById(carId);
		if (optionalCar.isPresent()) {
			Optional<Tire> optionalTire = tireRepository.findById(tireId);
			if (optionalTire.isPresent()) {
				Car carInDB = optionalCar.get();
				Tire tireInDB = optionalTire.get();
				if (carInDB.getTires().contains(tireInDB)) {
					tireRepository.delete(tireInDB);
					carInDB.getTires().remove(tireInDB);
					return carMapper.entityToDto(carInDB);
				}
			}
		}
		return null; // error handling
	}

}
