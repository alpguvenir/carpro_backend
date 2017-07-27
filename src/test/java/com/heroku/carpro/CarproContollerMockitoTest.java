package com.heroku.carpro;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heroku.carpro.controller.CarproController;
import com.heroku.carpro.entity.Carpro;
import com.heroku.carpro.service.CarproService;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)

public class CarproContollerMockitoTest {

	// Vehicle class potential values for the attributes are stored below
	private final List<String> brandList = new ArrayList<>(Arrays.asList("Mercedes", "Opel", "BMW", "Audi", "VW", "Maserati", "Porsche", "Honda"));
	private final List<Integer> yearEstablishedList = new ArrayList<>(Arrays.asList(2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017)); 
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Mock
	private CarproService carproService;
	
	@InjectMocks
	private CarproController carproController;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAllCarprosTest() {
		
		String brand;
		int yearEstablished;
		List<Carpro> mockList = new ArrayList<>();
		
		brand = brandList.get(0);
		yearEstablished = yearEstablishedList.get(2);
		Carpro testObj1 = Carpro.builder().id(1).brand(brand).yearEstablished(yearEstablished).build();
		mockList.add(testObj1);
		
		brand = brandList.get(1);
		yearEstablished = yearEstablishedList.get(5);
		Carpro testObj2 = Carpro.builder().id(2).brand(brand).yearEstablished(yearEstablished).build();
		mockList.add(testObj2);
		
		when(carproService.getAllCarpros()).thenReturn(mockList);
		
		String returnList = carproController.getAllCarpros();
		assertEquals(mockList.toString(), returnList);
		
	}
	
	/*
	@Test
	public void addCarproTest() {
		String brand;
		int yearEstablished;
		
		brand = brandList.get(0);
		yearEstablished = yearEstablishedList.get(2);
		Carpro mockObj = Carpro.builder().id(1).brand(brand).yearEstablished(yearEstablished).build();
		
		when(carproService.addCarpro(mockObj)).thenReturn(mockObj);
		
		ResponseEntity<Carpro> returnObj = carproController.addCarpro(mockObj);
		
		System.out.println(returnObj);
		
		Carpro castReturnObj = null;
		try {
			castReturnObj = mapper.readValue(returnObj.getBody().toString(), Carpro.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(castReturnObj);
		
		assertEquals(1, castReturnObj.getId());
		assertEquals("Mercedes", castReturnObj.getBrand());
		assertEquals(2012, castReturnObj.getYearEstablished());
	}
	
	@Test
	public void updateVehicleByIdTest() {
		String brand;
		int yearEstablished;
		
		brand = brandList.get(0);
		yearEstablished = yearEstablishedList.get(2);
		Vehicle mockObj = Vehicle.builder().id(1).brand(brand).yearEstablished(yearEstablished).build();
		
		brand = brandList.get(0);
		yearEstablished = yearEstablishedList.get(7);
		Vehicle updatedObj = Vehicle.builder().id(1).brand(brand).yearEstablished(yearEstablished).build();
				
		when(vehicleService.updateVehicleById(1, mockObj)).thenReturn(updatedObj);
		
		Vehicle returnObj = vehicleController.updateVehicleById(1, mockObj);
		assertEquals(updatedObj, returnObj);
		
	}
	
	@Test
	public void deleteVehicleByIdTest() {
		String brand;
		int yearEstablished;
		
		brand = brandList.get(0);
		yearEstablished = yearEstablishedList.get(2);
		Vehicle testObj1 = Vehicle.builder().id(1).brand(brand).yearEstablished(yearEstablished).build();
		
		vehicleController.deleteVehicleById(testObj1.getId());
		verify(vehicleService).deleteVehicleById(1);
		
	}
	
	@Test
	public void getVehicleByIdTest() {
		String brand;
		int yearEstablished;
		
		brand = brandList.get(1);
		yearEstablished = yearEstablishedList.get(5);
		Vehicle testObj1 = Vehicle.builder().id(1).brand(brand).yearEstablished(yearEstablished).build();
		
		when(vehicleService.getVehicleById(testObj1.getId())).thenReturn(testObj1);
		Vehicle returnObj = vehicleController.getVehicleById(1);
		assertEquals(1, returnObj.getId());
		assertEquals(brand, returnObj.getBrand());
		assertEquals(yearEstablished, returnObj.getYearEstablished());
	}
	*/
}
