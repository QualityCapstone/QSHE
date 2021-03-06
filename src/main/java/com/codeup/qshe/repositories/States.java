package com.codeup.qshe.repositories;

import com.codeup.qshe.models.State;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface States extends CrudRepository <State, Long> {

   List<State> findAll();

   State findByName(String name);

   State findByAbbr(String abbr);

   State findById(long id);

   @Query(nativeQuery=true, value="SELECT * FROM state ORDER BY rand() LIMIT 1")
   State getRandom();


}
