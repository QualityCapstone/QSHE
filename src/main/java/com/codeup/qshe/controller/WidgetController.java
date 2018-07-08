package com.codeup.qshe.controller;

import com.codeup.qshe.models.State;
import com.codeup.qshe.models.user.Message;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.repositories.Users;
import com.codeup.qshe.services.StateService;
import com.codeup.qshe.services.user.MessagesService;
import com.codeup.qshe.services.user.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WidgetController {
    private MessagesService messagesService;
    private Users userDao;

    public WidgetController(Users userDao, MessagesService messagesService){
        this.userDao = userDao;
        this.messagesService= messagesService;
    }


    @GetMapping("/widget")
    public String viewForum(Model view){
    List<Message> messages = messagesService.findAll();
    view.addAttribute("messages", messages);
    return "/widget";
    }

    @GetMapping("/widget/all")
    public String showPostForum(Model model){
        model.addAttribute("newPost", new Message());
        List<Message> messages = messagesService.findAll();
        model.addAttribute("messages", messages);
        return "widget/all";
    }


    @PostMapping("/widget/all")
    public String createPost (@RequestParam(name = "message") String userInput){
        Message message = new Message();
        message.setMessage(userInput);
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findById(sessionUser.getId()).get();
        message.setSender(user);
        messagesService.save(message);
        return "redirect:/widget/all";
    }

    @DeleteMapping("/widget/{id}/delete")
    public String deleteMessage(@PathVariable long id){
        messagesService.findOne(id);
        messagesService.deleteMessage(id);

        return "redirect: /widget";
    }


//    @PostMapping("/widget/{id}/delete")
//    public String delete(@PathVariable long id){
//        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = userDao.findById(sessionUser.getId()).get();
//        Message message = messagesService.findOne(id);
//        if (message.getSender().getId() != user.getId()) {
//            return "redirect:/login";
//        } else
//            messagesService.delete(id);
//        return "redirect:/widget";
//    }


}


