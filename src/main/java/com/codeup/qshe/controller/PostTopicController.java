package com.codeup.qshe.controller;

import com.codeup.qshe.models.State;
import com.codeup.qshe.models.user.Post;
import com.codeup.qshe.models.user.PostTopic;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.services.PostService;
import com.codeup.qshe.services.PostTopicService;
import com.codeup.qshe.repositories.Posts;
import com.codeup.qshe.services.StateService;
import com.codeup.qshe.services.user.UserService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("posts/")
public class PostTopicController {
    private final PostService postDao;
    private final UserService userDao;
    private final StateService stateDao;
    private final PostTopicService topicDao;


    public PostTopicController(PostService postDao, UserService userDao, StateService stateDao,PostTopicService topicDao) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.stateDao = stateDao;
        this.topicDao =topicDao;

    }


    @GetMapping("state/{abbr}")
    private String getStatePosts(@PathVariable String abbr, Model model, @PageableDefault(value = 10) Pageable pageable) {

        State state = stateDao.getStates().findByAbbr(abbr);
        model.addAttribute("state", state);
        model.addAttribute("posts", postDao.getTopics().findAllByState(state, pageable));

        return "posts/all";
    }

    @PostMapping("topic/add")
    public String createTopic(@RequestParam(name = "title") String title,
                              @RequestParam(name = "state-abbr") String abbr) {

        User user = userDao.getLoggedInUser();
        State state = stateDao.getStates().findByAbbr(abbr);

        PostTopic topic = new PostTopic(user,title,state);
        Post post = new Post(topic,user,title);

        topicDao.save(topic);




        return "redirect:/posts/state/" + state.getAbbr();
    }

//    @PostMapping("/top")
//    public String createPost(@RequestParam(name = "message")String message,
//                             @RequestParam(name = "topic") PostTopic topic) {
//        User user = userDao.getLoggedInUser();
//        PostTopic posttopic = postDao.findAllByTitle(topic);
//
//        Post post = new Post();
//        posts.save(post);
//        return "/";
//    }



//
//    @PostMapping("/posts/{id}/all")
//    public String createPost (@PathVariable long id,
//                              @RequestParam(name= "blogpost") String blogpost,
//                               Model model){
//        System.out.println("hello posts");
//        User user = userDao.getLoggedInUser();
//        State state = stateDao.findById(id).get();
//
//        PostTopic postTopic = new PostTopic();
//        model.addAttribute("blogpost", postTopic);
//        postTopic.setBody(blogpost);
//        postTopic.setCreatedAt(LocalDateTime.now());
//
//        postTopic.setTitle(state.getName());
//        postTopic.setStateId(state.getId());
//        postTopic.setUser(user);
//
//
//        postDao.save(postTopic);
//        return "redirect:/posts/all/{id}";
//    }
//
    @DeleteMapping("/topic/{id}/delete")
    public String deletePost(@PathVariable long id) {
        PostTopic topic = postDao.getTopics().findById(id);
        postDao.getTopics().delete(topic);
        return "redirect: /state/" + topic.getState().getAbbr();
    }

}
