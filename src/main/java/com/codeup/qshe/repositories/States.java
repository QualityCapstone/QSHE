package com.codeup.qshe.repositories;

import com.codeup.qshe.models.State;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface States extends CrudRepository <State, Long> {

   List<State> findAll();

   State findByName(String name);



}
