package com.heroku.carpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
      return "Home page!";
    }

	@GetMapping("/cars")
	public String getAllCarpros() {
		return this.carproService.getAllCarpros().toString();
	}
	
	@PostMapping("/cars")
	public ResponseEntity<Carpro> addCarpro(@RequestBody Carpro carpro) {
		Carpro json = this.carproService.addCarpro(carpro);
		if (json == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		return ResponseEntity.ok(json);
	}
	
	@GetMapping("/cars/{id}")
	public ResponseEntity<Carpro> getCarproById(@PathVariable int id) {
		Carpro json = this.carproService.getCarproById(id);
		if (json == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(json);
	}
	
	@PutMapping("/cars/{id}")
	public ResponseEntity<Carpro> updateCarproById(@PathVariable int id, @RequestBody Carpro car) {
		Carpro json = this.carproService.updateCarproById(id, car);
		if (json == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(json);
	}
	
	@DeleteMapping("/cars/{id}")
	public ResponseEntity<Carpro> deleteCarproById(@PathVariable int id) {
		boolean success = this.carproService.deleteCarproById(id);
		if(success)
			return ResponseEntity.status(HttpStatus.OK).body(null);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
}
