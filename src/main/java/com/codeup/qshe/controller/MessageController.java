package com.codeup.qshe.controller;


import com.codeup.qshe.models.user.Message;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.repositories.MessageRepository;
import com.codeup.qshe.repositories.Users;
import com.codeup.qshe.services.user.MessagesService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MessageController {

     private final MessagesService messagesService;
     private final Users users;

     public MessageController(MessagesService messagesService, Users users){
         this.messagesService = messagesService;
         this.users = users;
     }


     @GetMapping("/messages")
        public String viewMessages(Model view) {
         List<Message> messages = messagesService.findAll();
         view.addAttribute("messages", messages);
         return "/messages";
     }

    @GetMapping("/messages/{id}")
    public String showMessage(@PathVariable long id, Model view){
        Message message = messagesService.findOne(id);
        view.addAttribute("message", message);

        return "messages/show";
    }


//     @PostMapping("/send/message")
//        public String postMessage(Model model, @Valid Message message, @RequestParam List<User> messageFrom){
//         User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//         message.setFrom(1L);
//         message.setMessage("message");
//
//         return "redirect:/profile";
//     }


//    @GetMapping("/messages/{id}/edit")
//    public String edit (@PathVariable long id, Model view){
//         User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//         User user = users.findById(sessionUser.getId());
//         Message message = messagesService.findOne(id);
//         if(message.getSender().getId() == user.getId()){
//             view.addAttribute("message", messagesService.findOne(id));
//             return "/messages/edit";
//         } else
//             return "redirect:/login";
//    }


   @PostMapping("/messages/{id}/edit")
    public String updateMessage(@PathVariable long id, @Valid Message messageDetails){
         Message message = messagesService.findOne(id);
         message.setMessage(messageDetails.getMessage());
         messagesService.save(message);

         return "redirect:/posts";
   }


   @DeleteMapping("/messages/{id}/delete")
   public String deleteMessage(@PathVariable long id){
         messagesService.findOne(id);
         messagesService.deleteMessage(id);

         return "redirect: /posts";
   }

//   @PostMapping("/messages/{id}/delete")
//    public String delete(@PathVariable long id){
//         User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//         User user = users.findById(sessionUser.getId());
//         Message message = messagesService.findOne(id);
//         if(message.getSender().getId() != user.getId()){
//             return "redirect:/login";
//         } else
//             messagesService.delete(id);
//                return "redirect:/messages";
//   }

   @GetMapping("/messages/create")
    public String showMessageForm(Model model){
         model.addAttribute(" message", new Message());
         return "messages/create";
   }

   @PostMapping("/messages/create")
    public String create(@ModelAttribute Message message){
         message.setSender(users.findByUsername("username"));
         messagesService.save(message);

         return "redirect:/messages";
   }






}
