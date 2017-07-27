package com.heroku.carpro.service;

import java.util.ArrayList;
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

	public List<Carpro> getAllCars() {
		List<Carpro> cars = new ArrayList<>();
		carproRepository.findAll().forEach(cars::add);
		return cars;
	}

	public Carpro addCar(Carpro carpro) {
		carpro.setId(0);
		return carproRepository.save(carpro);
	}

	public Carpro getCarById(int id) {
		return carproRepository.findOne(id);
	}
	
	public Carpro updateCarById(int id, Carpro carpro) {
		Carpro temp = carproRepository.findOne(id);
		if(temp != null) {
			temp.setId(id);
			temp.setBrand(carpro.getBrand());
			temp.setYearEstablished(carpro.getYearEstablished());
			return carproRepository.save(temp);
		}
		return null;
	}

	public boolean deleteCarById(int id) {
		if(carproRepository.findOne(id) != null) {
			carproRepository.delete(id);
			return true;
		}
		return false;
	}
	
}
