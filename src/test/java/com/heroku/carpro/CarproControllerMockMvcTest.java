package com.heroku.carpro;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heroku.carpro.entity.Carpro;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)

public class CarproControllerMockMvcTest {

	private final List<String> brandList = new ArrayList<>(Arrays.asList("Mercedes", "Opel", "BMW", "Audi", "VW", "Maserati", "Porsche", "Honda"));
	private final List<Integer> yearEstablishedList = new ArrayList<>(Arrays.asList(2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017)); 
		
	ObjectMapper mapper = new ObjectMapper();
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext wac;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	
	@Test
	public void getAllVehiclesTest() throws Exception {
		String list = "";
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/cars")
												.accept(MediaType.APPLICATION_JSON))
												.andDo(print())
												.andReturn();
		
		assertEquals(list, result.getResponse().getContentAsString());
	}
	
	
	@Test
	public void addVehicleTest() throws Exception {
		final String brand = brandList.get(0);
		final int yearEstablished = yearEstablishedList.get(2);
		
		Carpro trial = Carpro.builder().id(0).brand(brand).yearEstablished(yearEstablished).build();
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/cars")
												.contentType(MediaType.APPLICATION_JSON)
												.content(mapper.writeValueAsString(trial))
												.accept(MediaType.APPLICATION_JSON))
												.andDo(print())
												.andReturn();

		//ResponseEntity<List<Carpro>> returnList = carproController.getAllCarpros();
		
		Carpro trialReturn = mapper.readValue(result.getResponse().getContentAsString(), Carpro.class);
		trial.setId(trialReturn.getId());
		
		// Comparing the Vehicle object passed in the form of JSON
		// with the return value of the POST method in the controller
		assertEquals(trial, trialReturn);
	}
	
	@Test
	public void updateVehicleByIdTest() throws Exception {
		final String brand = brandList.get(0);
		int yearEstablished = yearEstablishedList.get(2);
		
		Carpro trial = Carpro.builder().id(0).brand(brand).yearEstablished(yearEstablished).build();
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/cars")
												.contentType(MediaType.APPLICATION_JSON)
												.content(mapper.writeValueAsString(trial))
												.accept(MediaType.APPLICATION_JSON))
												.andDo(print())
												.andReturn();

		Carpro trialReturn = mapper.readValue(result.getResponse().getContentAsString(), Carpro.class);
		trial.setId(trialReturn.getId());
		
		yearEstablished = yearEstablishedList.get(5);
		Carpro trialUpdated = Carpro.builder().id(trialReturn.getId()).brand(brand).yearEstablished(yearEstablished).build();
		
		MvcResult resultUpdate = mockMvc.perform(MockMvcRequestBuilders.put("/cars/1")
												.contentType(MediaType.APPLICATION_JSON)
												.content(mapper.writeValueAsString(trialUpdated))
												.accept(MediaType.APPLICATION_JSON))
												.andDo(print())
												.andReturn();

		Carpro trialReturnUpdate = mapper.readValue(resultUpdate.getResponse().getContentAsString(), Carpro.class);
		
		assertEquals(trialUpdated, trialReturnUpdate);
	}
	
	
}
