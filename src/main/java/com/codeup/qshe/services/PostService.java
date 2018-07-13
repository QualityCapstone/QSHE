package com.codeup.qshe.services;
import com.codeup.qshe.models.user.PostTopic;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.repositories.PostTopics;
import com.codeup.qshe.repositories.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PostService {
   private PostTopics postDao;
   private Users userDao;

    @Autowired
    public PostService(PostTopics postDao, Users userDao){

        this.postDao = postDao;
        this.userDao = userDao;
    }

    public List<PostTopic> findAll() {
        Iterable<PostTopic> posts = getPosts().findAll();
        return (List<PostTopic>) posts;
    }

    public PostTopics getPosts(){return postDao;}



    public PostTopic save(PostTopic postTopic){

        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        postTopic.setUser(sessionUser);
        postDao.save(postTopic);

        return postTopic;
    }

    public PostTopic deletePost(long id){
        PostTopic postTopic = postDao.findById(id).get();
        postDao.delete(postTopic);
        return postTopic;
    }

    public void delete(long id){
        postDao.delete(deletePost(id));
    }
}
