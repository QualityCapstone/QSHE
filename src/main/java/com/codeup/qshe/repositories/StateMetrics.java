package com.codeup.qshe.repositories;

import com.codeup.qshe.models.State;
import com.codeup.qshe.models.user.StateMetric;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StateMetrics extends CrudRepository<StateMetric, Long> {



    List<StateMetric> findAll();

    StateMetric findByName(String name);


//    @Query("select  " +
//            "((1 - (min(graduate_count) / max(graduate_count))) * 100) " +
//            "from StateEducation e where e.state  = ?1")
//    Float percentPopulationIncreaseByState(State state);


    @Query("select  " +
            "((1 - (min(population) / max(population))) * 100) " +
            "from StatePopulation p where p.state  = ?1")
    Float percentPopulationIncreaseByState(State state);


}
