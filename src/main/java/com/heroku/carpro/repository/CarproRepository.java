package com.heroku.carpro.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.heroku.carpro.entity.Carpro;

@Repository
public interface CarproRepository extends CrudRepository<Carpro, Integer> {

}
