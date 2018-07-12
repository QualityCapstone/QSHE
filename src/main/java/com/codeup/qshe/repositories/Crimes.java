package com.codeup.qshe.repositories;

import com.codeup.qshe.models.State;
import com.codeup.qshe.models.StateCrime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Crimes extends CrudRepository<StateCrime, Long>{
 //second test


    List<StateCrime> findAllByState(State state);

}
