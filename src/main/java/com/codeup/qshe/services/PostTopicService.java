package com.codeup.qshe.services;
import com.codeup.qshe.models.user.Post;
import com.codeup.qshe.models.user.PostTopic;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.repositories.PostTopics;
import com.codeup.qshe.repositories.Posts;
import com.codeup.qshe.repositories.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PostTopicService {
   private Posts posts;
   private PostTopics postDao;
   private Users userDao;

    @Autowired
    public PostTopicService(PostTopics postDao, Users userDao, Posts posts){
        this.posts = posts;
        this.postDao = postDao;
        this.userDao = userDao;
    }

    public List<PostTopic> findAll() {
        Iterable<PostTopic> posts = getPosts().findAll();
        return (List<PostTopic>) posts;
    }

    public PostTopics getPosts(){return postDao;}

    public List<PostTopic> findAllByTitle(String title){
        Iterable<PostTopic> posts = getPosts().findAllByTitle(title);
        return (List<PostTopic>) posts;
    }

    public List<PostTopic> findAllByTopic(PostTopic topic){
        Iterable<PostTopic> posts = postDao.findAllByTopic(topic);
        return (List<PostTopic>) posts;
    }

    public PostTopic findById(long id){

        PostTopic topic = postDao.findById(id);
        return topic;
    }



    public PostTopic deletePost(long id){
        PostTopic postTopic = postDao.findById(id);
        postDao.delete(postTopic);
        return postTopic;
    }

    public void delete(long id){
        postDao.delete(deletePost(id));
    }

    public PostTopic findAllByTitle(PostTopic topic) {
        return topic;
    }
}
