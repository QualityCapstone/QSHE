package com.codeup.qshe.repositories;

import com.codeup.qshe.models.StatePopulation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatePopulations extends CrudRepository<StatePopulation, Long> {


}
