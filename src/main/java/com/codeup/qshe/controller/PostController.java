package com.codeup.qshe.controller;

import com.codeup.qshe.models.user.Post;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.services.PostService;
import com.codeup.qshe.services.user.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {
    private final PostService postDao;
    private final UserService userDao;

    public PostController(PostService postDao, UserService userDao){
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping("/posts/all/{id}")
    private String viewPosts(@PathVariable long id,Model model) {
        List<Post> posts = postDao.findAllByStateId(id);
        model.addAttribute("posts", posts);
        return "posts/all";
    }

//    @GetMapping("/posts/all")
//    private String create( Model model){
//
//        System.out.println("hello new post");
//        model.addAttribute("blogpost", new Post());
//        return "posts/all";
//    }

    @PostMapping("/posts/all")
    public String createPost (@RequestParam(name = "blogpost") String userInput){
        System.out.println("hello posts");
        User user = userDao.getLoggedInUser();
        Post post = new Post();
        post.setBody(userInput);
        post.setUser(user);
        postDao.save(post);
        return "posts/all";
    }

}
