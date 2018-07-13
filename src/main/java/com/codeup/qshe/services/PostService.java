package com.codeup.qshe.services;
import com.codeup.qshe.models.user.Post;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.repositories.Posts;
import com.codeup.qshe.repositories.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PostService {
   private Posts postDao;
   private Users userDao;

    @Autowired
    public PostService(Posts postDao, Users userDao){

        this.postDao = postDao;
        this.userDao = userDao;
    }

    public Post findOne(long id){
        Post post = postDao.findById(id).get();
    return post;
    }


    public List<Post> findAll() {
        Iterable<Post> posts = getPosts().findAll();
        return (List<Post>) posts;
    }


    public List<Post> findAllByStateId(long id) {
        Iterable<Post> posts = getPosts().findAllByStateId(id);
        return (List<Post>) posts;
    }

    public Posts getPosts(){return postDao;}



    public Post save(Post post){

        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(sessionUser);
        postDao.save(post);

        return post;
    }

    public Post deletePost(long id){
        Post post = postDao.findById(id).get();
        postDao.delete(post);
        return post;
    }

    public void delete(long id){
        postDao.delete(deletePost(id));
    }
}
