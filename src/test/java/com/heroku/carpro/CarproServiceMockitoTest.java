package com.heroku.carpro;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.heroku.carpro.entity.Carpro;
import com.heroku.carpro.repository.CarproRepository;
import com.heroku.carpro.service.CarproService;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)

public class CarproServiceMockitoTest {

	// Vehicle class potential values for the attributes are stored below
	private final List<String> brandList = new ArrayList<>(Arrays.asList("Mercedes", "Opel", "BMW", "Audi", "VW", "Maserati", "Porsche", "Honda"));
	private final List<Integer> yearEstablishedList = new ArrayList<>(Arrays.asList(2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017)); 
		
	@Mock
	private CarproRepository carproRepository;
	
	@InjectMocks
	private CarproService carproService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAllVehiclesTest() {
		
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
	
		when(carproRepository.findAll()).thenReturn(mockList);
		
		List<Carpro> returnList = carproService.getAllCarpros();
		assertEquals(mockList, returnList);
		
	}
	
	
	@Test
	public void addVehicleTest() {
		String brand;
		int yearEstablished;
		
		brand = brandList.get(0);
		yearEstablished = yearEstablishedList.get(2);
		Carpro mockObj = Carpro.builder().id(0).brand(brand).yearEstablished(yearEstablished).build();
		
		when(carproRepository.save(mockObj)).thenReturn(mockObj);
		
		Carpro returnObj = carproService.addCarpro(mockObj);
		assertEquals(mockObj, returnObj);
	}
	
}


