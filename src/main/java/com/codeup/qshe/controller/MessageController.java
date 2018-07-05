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

   @PostMapping("/messages/{id}/edit")
    public String updateMessage(@PathVariable long id, @Valid Message messageDetails){
         Message message = messagesService.findOne(id);
         message.setMessage(messageDetails.getMessage());
         messagesService.save(message);

         return "redirect:/messages";
   }


   @DeleteMapping("/messages/{id}/delete")
   public String deleteMessage(@PathVariable long id){
         messagesService.findOne(id);
         messagesService.deleteMessage(id);

         return "redirect: /messages";
   }


   @GetMapping("/messages/create")
    public String showMessageForm(Model model){
         model.addAttribute("newMessage", new Message());
       List<Message> messages = messagesService.findAll();
       model.addAttribute("messages", messages);
         return "messages/create";
   }

   @PostMapping("/messages/create")
    public String create(@RequestParam(name = "message") String userInput){
         Message message = new Message();
         message.setMessage(userInput);
         User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         User user = users.findById(sessionUser.getId()).get();
         message.setSender(user);
//         message.setRecipient(user.getId());
         messagesService.save(message);

         return "redirect:/messages/create";
   }






}
