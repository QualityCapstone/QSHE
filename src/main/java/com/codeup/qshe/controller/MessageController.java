package com.codeup.qshe.controller;


import com.codeup.qshe.models.user.Message;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.services.messages.MessagesService;
import com.codeup.qshe.services.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MessageController {

     private final MessagesService messageDao;
     private final UserService userDao;

     public MessageController(MessagesService messageDao, UserService userDao){
         this.messageDao = messageDao;
         this.userDao = userDao;
     }


     // Conversation between two users
    @GetMapping("/messages/view/{id}")
     private String viewConversation(@PathVariable Long id, Model model) {

         User user = userDao.getLoggedInUser();
         User recipient = userDao.getUsers().findById(id).get();

       model.addAttribute("messages", messageDao.getMessages().findAllByRecipientAndSender(recipient, user));
       model.addAttribute("recipient", recipient);

         return "/messages/view";
     }


   @DeleteMapping("/messages/{id}/delete")
   public String deleteMessage(@PathVariable long id){
       messageDao.findOne(id);
       messageDao.deleteMessage(id);

         return "redirect: /messages";
   }


   @PostMapping("/messages/create")
    public String create(@RequestParam(name = "recipient-id") String sentToId,
                         @RequestParam(name = "message") String userInput, Model model){

         User user = userDao.getLoggedInUser();
         User recipient = userDao.getUsers().findById(Long.parseLong(sentToId)).get();
         Message message = new Message(user, recipient, userInput);
         messageDao.save(message);
         model.addAttribute("recipient", recipient);

         return "redirect:/messages/view/" + sentToId;
   }






}
