package com.codeup.qshe.repositories;

import com.codeup.qshe.models.user.Post;
import com.codeup.qshe.models.user.PostTopic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Posts extends CrudRepository <Post, Long> {


    List<Post> findAllByTopic(PostTopic topic);

    Post findByTopic(PostTopic topic);

    Post findById(long id);


}
