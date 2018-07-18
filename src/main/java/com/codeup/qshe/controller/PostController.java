package com.codeup.qshe.controller;

import com.codeup.qshe.models.user.Post;
import com.codeup.qshe.models.user.PostTopic;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.repositories.Posts;
import com.codeup.qshe.repositories.States;
import com.codeup.qshe.repositories.Users;
import com.codeup.qshe.services.PostService;
import com.codeup.qshe.services.PostTopicService;
import com.codeup.qshe.services.StateService;
import com.codeup.qshe.services.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PostController {

    private final PostService postDao;
    private final StateService stateDao;
    private final Posts posts;
    private final UserService userDao;
    private final PostTopicService topicDao;

    public PostController(PostService postDao,StateService stateDao,
                          Posts posts,UserService userDao,PostTopicService topicDao){
        this.postDao = postDao;
        this.stateDao = stateDao;
        this.posts = posts;
        this.userDao = userDao;
        this.topicDao = topicDao;
    }

    @GetMapping("/topic/state/{id}")
    public String viewDiscussion(@PathVariable Long id, Model model) {

        PostTopic topic = topicDao.findById(id);
        model.addAttribute("topic" , topic);
        model.addAttribute("posts",posts.findAllByTopic(topic));

        return "posts/topic";
    }

    @PostMapping("/topic/state/{id}")
    public String addPost(@PathVariable long id,
                          @RequestParam(name = "message") String message, Model model){
        User user = userDao.getLoggedInUser();
        PostTopic topic = topicDao.findById(id);
        Post post = new Post(topic,user, message);
        model.addAttribute("newposts", post);
        postDao.getPosts().save(post);

        return "redirect:/topic/state/" + id;
    }



}
