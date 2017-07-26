package com.heroku.carpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@GetMapping("/getAllCars")
	@ResponseBody
	public List<Carpro> getAllCars() {
		return this.carproService.getAllCars();
	}
	
	
	
	
}
