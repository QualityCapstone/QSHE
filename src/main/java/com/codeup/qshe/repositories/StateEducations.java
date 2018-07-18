package com.codeup.qshe.repositories;

import com.codeup.qshe.models.State;
import com.codeup.qshe.models.StateEducation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateEducations extends CrudRepository<StateEducation, Long> {

    List<StateEducation> findAllByState(State state);


    @Query(value = "SELECT s.abbr FROM  state_education se " +
            "  INNER JOIN state s ON  s.id = se.state_id " +
            "GROUP BY s.abbr " +
            "ORDER BY sum(graduate_count) DESC", nativeQuery = true)
    List<String> stateEducationMostToLeast();

}
