package com.codeup.qshe.repositories;

import com.codeup.qshe.models.State;
import com.codeup.qshe.models.user.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Posts extends CrudRepository <Post, Long> {

    List<Post> findAll();

    List<Post> findAllByStateId(long id);

    List<Post> findAllByStateId(long id, Pageable pageable);

    List<Post> findTop3ByStateId(long id);





}
