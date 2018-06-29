package com.codeup.qshe.repositories;

import com.codeup.qshe.models.user.State;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.models.user.UserConnection;
import com.codeup.qshe.models.user.UserProfile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface States extends CrudRepository <State, Long>{

    State findByName(String name);

}
