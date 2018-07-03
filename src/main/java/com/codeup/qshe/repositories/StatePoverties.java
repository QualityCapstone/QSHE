package com.codeup.qshe.repositories;

import com.codeup.qshe.models.StatePoverty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatePoverties extends CrudRepository<StatePoverty, Long> {

}
