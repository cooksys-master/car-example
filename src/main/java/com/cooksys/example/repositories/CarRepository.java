package com.cooksys.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.example.entities.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

}
