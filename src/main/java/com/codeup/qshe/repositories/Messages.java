package com.codeup.qshe.repositories;

import com.codeup.qshe.models.user.Message;
import com.codeup.qshe.models.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Messages extends CrudRepository <Message, Long>{


    @Query(nativeQuery = true, value="SELECT * FROM user LIMIT 1") // To insert any user without taking care of the id
    User first();


    @Query(nativeQuery = true, value="SELECT * FROM message WHERE senderId=? AND recipientId=?")
    Message getRelated(Long senderId, Long recipientId );



    List<Message> findAllByRecipientAndSender(User r, User s);


    List<Message> findAllBySender(User sender);


}
