package com.heroku.carpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.heroku.carpro.entity.Carpro;
import com.heroku.carpro.service.CarproService;

@RestController
public class CarproController {
	
	private CarproService carproService;

	@Autowired
	public CarproController(CarproService carproService) {
		this.carproService = carproService;
	}
	
	@GetMapping("/")
    String home() {
      return "Hello World!";
    }
	
	@GetMapping("/getallcars")
	public String getAllCars() {
		return this.carproService.getAllCars().toString();
	}
	
	@GetMapping("/cars")
	public String getCars() {
		return "Cars a dair hersey";
	}
	
	@PostMapping("/cars")
	public ResponseEntity<Carpro> addCar(@RequestBody Carpro carpro) {
		Carpro json = this.carproService.addCar(carpro);
		if (json == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		return ResponseEntity.ok(json);
	}
	
	@GetMapping("/cars/{id}")
	public ResponseEntity<Carpro> getCarById(@PathVariable int id) {
		Carpro json = this.carproService.getCarById(id);
		if (json == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(json);
	}
	
}
