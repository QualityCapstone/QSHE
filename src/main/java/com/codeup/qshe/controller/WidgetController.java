package com.codeup.qshe.controller;

import com.codeup.qshe.services.PostTopicService;
import com.codeup.qshe.repositories.Users;
import org.springframework.stereotype.Controller;

@Controller
public class WidgetController {
    private PostTopicService postService;
    private Users userDao;

    public WidgetController(Users userDao, PostTopicService postTopicService){
        this.userDao = userDao;
        this.postService= postTopicService;
    }

//    @DeleteMapping("/posts/{id}/delete")
//    public String deleteMessage(@PathVariable long id){
//        postService.findOne(id);
//        postService.deletePost(id);
//
//        return "redirect: /posts";
//    }


//    @PostMapping("/posts/{id}/delete")
//    public String delete(@PathVariable long id){
//        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = userDao.findById(sessionUser.getId()).get();
//        Message message = messagesService.findOne(id);
////        if (message.getSender().getId() != user.getId()) {
////            return "redirect:/login";
////        } else
//            messagesService.delete(id);
//        return "redirect:/posts";
//    }


}


