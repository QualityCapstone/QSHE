package com.codeup.qshe.repositories;

import com.codeup.qshe.models.user.UserRating;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRatingRepository extends CrudRepository <UserRating, Long> {

    UserRating findByUserId(long userId);

    List<UserRating> findAll();

}
