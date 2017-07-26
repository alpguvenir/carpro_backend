package com.heroku.carpro.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


// Telling that Vehicle class is an Entity in my relational DB

@Entity
// The getters and setter are created with lombok
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Carpro {

	// Telling that this is a primary key that is auto incremented
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String brand;
	private int yearEstablished;
	
	public int compareTo(Carpro carpro) {
		return id - carpro.getId();
	}
	
}
