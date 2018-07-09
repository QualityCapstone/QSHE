package com.codeup.qshe.repositories;


import com.codeup.qshe.models.user.StateUserRating;
import com.codeup.qshe.models.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRatingRepository extends CrudRepository <StateUserRating, Long> {

    StateUserRating findByUserId(long userId);

//    User findByUsername(String username);

    List<StateUserRating> findAll();

    StateUserRating findByUserId(User userId);

    @Query(nativeQuery = true, value="SELECT * FROM state_user_rating LIMIT 1") // To insert any user without taking care of the id
    User first();

}
