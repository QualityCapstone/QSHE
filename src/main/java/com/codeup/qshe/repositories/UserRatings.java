package com.codeup.qshe.repositories;

import com.codeup.qshe.models.State;
import com.codeup.qshe.models.user.StateMetric;
import com.codeup.qshe.models.user.StateUserRating;
import com.codeup.qshe.models.user.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

@Repository
public interface UserRatings extends CrudRepository<StateUserRating, Long> {


    @Modifying
    @Transactional
    void deleteByStateAndUser(State state, User user);

    @Modifying
    @Transactional
    @Query(value="INSERT into StateMetric set name = ?1", nativeQuery = true)
    void createMetric(String name);


    List<StateUserRating> findAllByState(State state);

    @Query("select avg(u.rating) from StateUserRating u where u.state = ?1 and u.metric = ?2")
    Float avgRatingByStateAndMetric(State state, StateMetric metric);

    @Query("select avg(u.rating) from StateUserRating u where u.state = ?1 and u.user = ?2")
    Float avgUserRatingByState(State state, User user);

}
