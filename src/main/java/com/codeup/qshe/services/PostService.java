package com.codeup.qshe.services;

import com.codeup.qshe.models.user.Post;
import com.codeup.qshe.models.user.PostTopic;
import com.codeup.qshe.repositories.Posts;
import com.codeup.qshe.repositories.Users;
import com.codeup.qshe.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private Posts posts;
    private UserService userDao;


    public PostService(UserService userDao, Posts posts){
        this.posts = posts;
        this.userDao =userDao;

    }

    public Post findOne(long id){
        Post post = posts.findById(id);

        return post;
    }

    public Post findByTopic(PostTopic topic){
        Post post = posts.findByTopic(topic);
        return post;
    }

    public Posts getPosts(){
        return posts;
    }


}
