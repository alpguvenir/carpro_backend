package com.heroku.carpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heroku.carpro.entity.Carpro;
import com.heroku.carpro.service.CarproService;

@Controller
public class CarproController {
	
	private CarproService carproService;

	@Autowired
	public CarproController(CarproService carproService) {
		this.carproService = carproService;
	}
	
	@GetMapping("/")
    @ResponseBody
    String home() {
      return "Hello World!";
    }
	
	@GetMapping("/cars")
	@ResponseBody
	public String getCars() {
		return "Cars a dair hersey";
	}
	
	@GetMapping("/getallcars")
	@ResponseBody
	public String getAllCars() {
		return this.carproService.getAllCars().toString();
	}
	
	@PostMapping("/cars")
	@ResponseBody
	public ResponseEntity<Carpro> addCar(@RequestBody Carpro carpro) {
		Carpro json = this.carproService.addCar(carpro);
		if (json == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		return ResponseEntity.ok(json);
	}
	
	
	
	
}
