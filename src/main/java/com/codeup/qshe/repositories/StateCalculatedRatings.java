package com.codeup.qshe.repositories;

import com.codeup.qshe.models.State;
import com.codeup.qshe.models.state.StateCalculatedRating;

import com.codeup.qshe.models.user.StateMetric;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateCalculatedRatings extends CrudRepository<StateCalculatedRating, Long> {

        List<StateCalculatedRating> findAllByState(State state);


        StateCalculatedRating findByStateAndMetric(State state, StateMetric metric);

}
