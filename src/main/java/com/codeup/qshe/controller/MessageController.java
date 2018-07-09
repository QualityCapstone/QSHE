package com.codeup.qshe.controller;


import com.codeup.qshe.models.user.Message;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.repositories.MessageRepository;
import com.codeup.qshe.repositories.Users;
import com.codeup.qshe.services.user.MessagesService;
import com.codeup.qshe.services.user.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MessageController {

     private final MessagesService messageDao;
     private final UserService userDao;

     public MessageController(MessagesService messageDao, UserService userDao){
         this.messageDao = messageDao;
         this.userDao = userDao;
     }


     @GetMapping("/messages")
        public String viewMessages(Model view) {
         List<Message> messages = messageDao.findAll();
         view.addAttribute("messages", messages);
         return "/messages";
     }

    @GetMapping("/messages/{id}")
    public String showMessage(@PathVariable long id, Model view){
        Message message = messageDao.findOne(id);
        view.addAttribute("message", message);

        return "messages/show";
    }

   @PostMapping("/messages/{id}/edit")
    public String updateMessage(@PathVariable long id, @Valid Message messageDetails){
         Message message = messageDao.findOne(id);
         message.setMessage(messageDetails.getMessage());
        messageDao.save(message);

         return "redirect:/messages";
   }


   @DeleteMapping("/messages/{id}/delete")
   public String deleteMessage(@PathVariable long id){
       messageDao.findOne(id);
       messageDao.deleteMessage(id);

         return "redirect: /messages";
   }


   @GetMapping("/messages/{id}/create")
    public String showMessageForm(@PathVariable Long id, Model model){
        User sender = userDao.getLoggedInUser();

      //  model.addAttribute("newMessage", new Message());


        List<Message> messages = messageDao.getMessageRepository().findAllBySender(sender);

        System.out.println(messages);

        model.addAttribute("messages", messages);

       User recipient = userDao.getUsers().findById(id).get();


       model.addAttribute("recipient", recipient);

         return "messages/create";
   }

   @PostMapping("/messages/{id}/create")
    public String create(@PathVariable Long id, @RequestParam(name = "message") String userInput, Model model){

         User user = userDao.getLoggedInUser();
         User recipient = userDao.getUsers().findById(id).get();

         Message message = new Message(user, recipient, userInput);
         messageDao.save(message);

         model.addAttribute("recipient", recipient);

         return "messages/messages";
   }






}
