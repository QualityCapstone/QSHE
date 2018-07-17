package com.codeup.qshe.repositories;

import com.codeup.qshe.models.State;
import com.codeup.qshe.models.StateCrime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Crimes extends CrudRepository<StateCrime, Long>{
 //second test

    List<StateCrime> findAllByState(State state);

    @Query(value = "select state_abbr " +
            "from state_crime group by state_abbr order by sum(violent_crime_count) / sum(state_crime.population) desc", nativeQuery = true)
    List<String> stateCrimeWorstToBest();

}
