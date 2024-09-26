package com.cooksys.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.example.entities.Tire;

@Repository
public interface TireRepository extends JpaRepository<Tire, Long> {
	
	

}
