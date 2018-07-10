package com.codeup.qshe.repositories;

import com.codeup.qshe.models.user.StateMetric;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StateMetrics extends CrudRepository<StateMetric, Long> {



    List<StateMetric> findAll();

    StateMetric findByName(String name);
}
