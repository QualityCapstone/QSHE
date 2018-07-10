package com.codeup.qshe.repositories;

import com.codeup.qshe.models.user.StateMetric;
import org.springframework.data.repository.CrudRepository;

public interface StateMetrics extends CrudRepository<StateMetric, Long> {


    StateMetric findByName(String name);
}
