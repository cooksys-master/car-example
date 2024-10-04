package com.cooksys.example.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.cooksys.example.dto.CarRequestDto;
import com.cooksys.example.dto.CarResponseDto;
import com.cooksys.example.dto.TireRequestDto;
import com.cooksys.example.dto.TireResponseDto;
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
	private final TireRepository tR;
	
	@Override
	public List<CarResponseDto> getAllCars() {
		List<Car> cars = carRepository.findAll();
		List<CarResponseDto> result = new ArrayList<>();
		for (Car c : cars) {
			CarResponseDto cRD = new CarResponseDto();
			cRD.setId(c.getId());
			cRD.setMake(c.getMake());
			cRD.setTires(new ArrayList<TireResponseDto>());
			for (Tire t : c.getTires()) {
				TireResponseDto tRD = new TireResponseDto();
				tRD.setId(t.getId());
				tRD.setManufacturer(t.getManufacturer());
				cRD.getTires().add(tRD);
			}
			result.add(cRD);
		}
		return result;
		// return carMapper.entitiesToDtos(carRepository.findAll());
	}

	@Override
	public CarResponseDto createCar(CarRequestDto carRD) {
		Car carToSave = carMapper.requestDtoToEntity(carRD);
		
		carRepository.save(carToSave);
		
		for (Tire t : carToSave.getTires()) {
			t.setCar(carToSave);
			tR.saveAndFlush(t);
		}
		
		return carMapper.entityToDto(carToSave);
	}

	@Override
	public CarResponseDto replaceTire(Long carId, Long tireId, TireRequestDto tireRequestDto) {
		Optional<Car> optionalCar = carRepository.findById(carId);
		if (optionalCar.isPresent()) {
			Optional<Tire> optionalTire = tR.findById(tireId);
			if (optionalTire.isPresent()) {
				Car carInDB = optionalCar.get();
				Tire tireInDB = optionalTire.get();
				if (carInDB.getTires().contains(tireInDB)) {
					tireInDB.setManufacturer(tireRequestDto.getManufacturer());
					tR.save(tireInDB);
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
				tR.delete(tire);
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
			Optional<Tire> optionalTire = tR.findById(tireId);
			if (optionalTire.isPresent()) {
				Car carInDB = optionalCar.get();
				Tire tireInDB = optionalTire.get();
				if (carInDB.getTires().contains(tireInDB)) {
					tR.delete(tireInDB);
					carInDB.getTires().remove(tireInDB);
					return carMapper.entityToDto(carInDB);
				}
			}
		}
		return null; // error handling
	}

}
