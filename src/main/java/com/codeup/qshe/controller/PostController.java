package com.codeup.qshe.controller;

import com.codeup.qshe.models.State;
import com.codeup.qshe.models.user.Post;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.repositories.Staterepository;
import com.codeup.qshe.services.PostService;

import com.codeup.qshe.services.user.UserService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
    private String viewPosts(@PathVariable long id, Model model, @PageableDefault(value=3) Pageable pageable) {
//        List<Post> posts = postDao.getPosts().findAllByStateId(id, pageable);

        model.addAttribute("posts", postDao.getPosts().findAllByStateId(id, pageable));

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
                               Model model){
        System.out.println("hello posts");
        User user = userDao.getLoggedInUser();
        State state = stateDao.findById(id).get();

        Post post = new Post();
        model.addAttribute("blogpost", post);
        post.setBody(blogpost);
        post.setCreatedAt(LocalDateTime.now());

        post.setTitle(state.getName());
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
    public String delete(@PathVariable long id,
                         @RequestParam(name = "state_id") long stateid){
        User currentuser = userDao.getLoggedInUser();
        Post post = postDao.findOne(id);
//        if (post.getUser().getId() != currentuser.getId()){
//            System.out.println("this message");
//            return "redirect:/login";
//        }else
        postDao.delete(id);
        return "redirect:/posts/all/"+ stateid;
    }

}
