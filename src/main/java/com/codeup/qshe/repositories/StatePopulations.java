package com.codeup.qshe.repositories;

import com.codeup.qshe.models.State;
import com.codeup.qshe.models.StatePopulation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatePopulations extends CrudRepository<StatePopulation, Long> {

    List<StatePopulation> findAllByState(State state);

}
