package com.codeup.qshe.repositories;

import com.codeup.qshe.models.State;
import com.codeup.qshe.models.StateEducation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateEducations extends CrudRepository<StateEducation, Long> {

    List<StateEducation> findAllByState(State state);

}
