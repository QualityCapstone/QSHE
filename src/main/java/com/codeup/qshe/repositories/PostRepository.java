package com.codeup.qshe.repositories;

import com.codeup.qshe.models.user.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository <Post, Long> {

    List<Post> findAll();

    List<Post> findAllByStateId(long id);

    List<Post> findAllByStateId(long id, Pageable pageable);








}
