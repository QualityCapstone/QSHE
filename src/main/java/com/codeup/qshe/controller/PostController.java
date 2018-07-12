package com.codeup.qshe.controller;

import com.codeup.qshe.models.State;
import com.codeup.qshe.models.user.Post;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.repositories.Staterepository;
import com.codeup.qshe.services.PostService;
import com.codeup.qshe.services.StateService;
import com.codeup.qshe.services.user.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {
    private final PostService postDao;
    private final UserService userDao;
    private final Staterepository stateDao;


    public PostController(PostService postDao, UserService userDao, Staterepository stateDao){
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

    @PostMapping("/posts/{id}/all")
    public String createPost (@PathVariable long id, @RequestParam(name = "title") String title,
                              @RequestParam(name= "blogpost") String blogpost,
                               @RequestParam(name ="topic") String topic,
                               Model model){
        System.out.println("hello posts");
        User user = userDao.getLoggedInUser();
        State state = stateDao.findById(id).get();
        Post post = new Post();
        model.addAttribute("blogpost", post);
        post.setCreatedAt(LocalDateTime.now());
        post.setBody(blogpost);
        post.setTitle(state.getName());
        post.setTopic(topic);
        post.setStateId(state.getId());
        post.setUser(user);
        postDao.save(post);
        return "redirect:/posts/all/{id}";
    }

    @DeleteMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id, Model model){

        model.addAttribute("post", postDao);
        postDao.findOne(id);
        postDao.deletePost(id);

        return "redirect: /posts/{id}/all";
    }

    @PostMapping("posts/{id}/delete")
    public String delete(@PathVariable long id){
        User currentuser = userDao.getLoggedInUser();
        Post post = postDao.findOne(id);
        if (post.getUser().getId() != currentuser.getId()){
            System.out.println("this message");
            return "redirect:/login";
        }else
            postDao.delete(id);
        return "redirect:/posts/{id}/all";
    }

}
