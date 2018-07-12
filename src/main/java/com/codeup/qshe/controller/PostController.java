package com.codeup.qshe.controller;

import com.codeup.qshe.models.State;
import com.codeup.qshe.models.user.Post;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.services.PostService;
import com.codeup.qshe.services.StateService;
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
    private final StateService stateDao;

    public PostController(PostService postDao, UserService userDao, StateService stateDao){
        this.postDao = postDao;
        this.userDao = userDao;
        this.stateDao =stateDao;
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
    public String createPost (@RequestParam(name = "blogpost") String userInput,
                              @RequestParam(name = "title") String title,
                              @RequestParam(name = "state") String state){
        System.out.println("hello posts");
        User user = userDao.getLoggedInUser();
     State thisState=stateDao.findByName(state);

        Post post = new Post();
        post.setTitle(title);
        post.setStateId(thisState.getId());
        post.setBody(userInput);
        post.setUser(user);
        postDao.save(post);
        return "redirect:/posts/all";
    }

}
