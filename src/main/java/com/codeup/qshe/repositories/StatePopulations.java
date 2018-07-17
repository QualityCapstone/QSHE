package com.codeup.qshe.repositories;

import com.codeup.qshe.models.State;
import com.codeup.qshe.models.StatePopulation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatePopulations extends CrudRepository<StatePopulation, Long> {

    List<StatePopulation> findAllByState(State state);


    @Query(value = "select s.abbr from  state_population se\n" +
            "  inner join state s ON  s.id = se.state_id\n" +
            "group by s.abbr\n" +
            "order by sum(population) desc;", nativeQuery = true)
    List<String> statePopulationMostToLeast();

}
