package com.codeup.qshe.repositories;

import com.codeup.qshe.models.StateCrime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrimeRepository extends CrudRepository<StateCrime, Long>{
}
