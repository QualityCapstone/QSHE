package com.codeup.qshe.repositories;

import com.codeup.qshe.models.user.StateMetric;
import com.codeup.qshe.models.user.StateUserRating;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;

public interface UserRatings extends CrudRepository<StateUserRating, Long> {


    @Modifying
    @Transactional
    @Query("Insert into StateMetric set name = ?1")
    void createMetric(String name);

}
