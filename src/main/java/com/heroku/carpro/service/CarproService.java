package com.heroku.carpro.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heroku.carpro.entity.Carpro;
import com.heroku.carpro.repository.CarproRepository;

@Service
public class CarproService {

	private CarproRepository carproRepository;
	
	@Autowired
	public CarproService (CarproRepository carproRepository) {
		this.carproRepository = carproRepository;
	}

	// Returning all cars in the db as in a sorted order based on id value
	public List<Carpro> getAllCarpros() {
		List<Carpro> cars = new ArrayList<>();
		carproRepository.findAll().forEach(cars::add);
		
		// Always show the list sorted
		Collections.sort(cars, new Comparator<Carpro>() {
			public int compare(Carpro carpro1, Carpro carpro2) {
				return carpro1.getId() - carpro2.getId();
			}
		}
		);
		
		return cars;
	}

	// Adding a car to the db
	public Carpro addCarpro(Carpro carpro) {
		carpro.setId(0);
		return carproRepository.save(carpro);
	}

	// Returning a car object based on the object
	public Carpro getCarproById(int id) {
		return carproRepository.findOne(id);
	}
	
	// Update a car by the id and return the updated car object
	public Carpro updateCarproById(int id, Carpro carpro) {
		Carpro temp = carproRepository.findOne(id);
		if(temp != null) {
			temp.setId(id);
			temp.setBrand(carpro.getBrand());
			temp.setYearEstablished(carpro.getYearEstablished());
			return carproRepository.save(temp);
		}
		return null;
	}

	// Delete a car object based on the id reference
	public boolean deleteCarproById(int id) {
		if(carproRepository.findOne(id) != null) {
			carproRepository.delete(id);
			return true;
		}
		return false;
	}
	
}
