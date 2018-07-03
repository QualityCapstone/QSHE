package com.codeup.qshe.repositories;

import com.codeup.qshe.models.user.Message;
import com.codeup.qshe.models.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends CrudRepository <Message, Long>{


    @Query(nativeQuery = true, value="SELECT * FROM user LIMIT 1") // To insert any user without taking care of the id
    User first();
}