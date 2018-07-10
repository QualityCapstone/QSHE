package com.codeup.qshe.repositories;

import com.codeup.qshe.models.user.StateMetric;
import com.codeup.qshe.models.user.StateUserRating;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
@Repository
public interface UserRatings extends CrudRepository<StateUserRating, Long> {


    @Modifying
    @Transactional
    @Query(value="INSERT into StateMetric set name = ?1", nativeQuery = true)
    void createMetric(String name);

}
