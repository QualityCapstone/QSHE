package com.codeup.qshe.repositories;

import com.codeup.qshe.models.State;

import com.codeup.qshe.models.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface Staterepository extends CrudRepository <State, Long> {

   List<State> findAll();

   State findByName(String name);


//   State findOne(long id);

   @Query(nativeQuery = true, value="SELECT * FROM state LIMIT 1") // To insert any user without taking care of the id
   State first();

}
