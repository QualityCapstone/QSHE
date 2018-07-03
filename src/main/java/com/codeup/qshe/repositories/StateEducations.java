package com.codeup.qshe.repositories;

import com.codeup.qshe.models.StateEducation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateEducations extends CrudRepository<StateEducation, Long> {
}
