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

    @GetMapping("/posts/all")
    private String viewPosts(Model model) {
        List<Post> posts = postDao.findAll();
        model.addAttribute("posts", posts);
        return "posts/all";
    }

    @PostMapping("/posts/all")
    public String createPost (@RequestParam(name = "post") String userInput){
        Post post = new Post();
        post.setBody(userInput);
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getLoggedInUser();
        post.setUser(user);
        postDao.save(post);
        return "redirect:/posts/all";
    }

}
