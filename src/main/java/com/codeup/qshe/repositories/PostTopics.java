package com.codeup.qshe.repositories;

import com.codeup.qshe.models.State;
import com.codeup.qshe.models.user.PostTopic;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostTopics extends CrudRepository <PostTopic, Long> {

    List<PostTopic> findAll();

    List<PostTopic> findAllByState(State state);

    List<PostTopic> findAllByState(State state, Pageable pageable);

    List<PostTopic> findTop4ByState(State state);
    List<PostTopic> findTop3ByState(State state);

    List<PostTopic> findAllByTitle(String title);

    List<PostTopic> findAllByTopic(PostTopic topic);

    PostTopic findById(long id);

    List<PostTopic> findAllByTopicOrderByIdDesc(PostTopic topic);





}
