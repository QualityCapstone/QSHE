package com.codeup.qshe.repositories;

import com.codeup.qshe.models.user.UserConnection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserConnections extends CrudRepository<UserConnection, Long> {

}