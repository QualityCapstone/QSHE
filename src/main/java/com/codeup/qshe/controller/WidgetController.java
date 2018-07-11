package com.codeup.qshe.controller;

import com.codeup.qshe.models.user.Post;
import com.codeup.qshe.services.PostService;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.repositories.Users;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class WidgetController {
    private PostService postService;
    private Users userDao;

    public WidgetController(Users userDao, PostService postService){
        this.userDao = userDao;
        this.postService= postService;
    }

    @DeleteMapping("/posts/{id}/delete")
    public String deleteMessage(@PathVariable long id){
        postService.findOne(id);
        postService.deletePost(id);

        return "redirect: /posts";
    }


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


