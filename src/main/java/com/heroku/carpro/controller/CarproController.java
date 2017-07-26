package com.heroku.carpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heroku.carpro.service.CarproService;

@Controller
public class CarproController {
	
	private CarproService carproService;

	@Autowired
	public CarproController(CarproService carproService) {
		this.carproService = carproService;
	}
	
	@RequestMapping("/")
    @ResponseBody
    String home() {
      return "Hello World!";
    }
	
	@GetMapping("/cars")
	@ResponseBody
	public String getAllCars() {
		return "Cars a dair hersey";
	}
	
	
	
	
}
