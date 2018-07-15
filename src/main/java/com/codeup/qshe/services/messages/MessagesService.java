package com.codeup.qshe.services.messages;

import com.codeup.qshe.models.user.Message;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.repositories.Messages;
import com.codeup.qshe.repositories.Users;
import com.codeup.qshe.services.user.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagesService {
    private Messages messages;
    private Users users;
    private UserService userDao;


    public MessagesService(Messages messages, Users users, UserService userDao){
        this.messages = messages;
        this.users = users;
        this.userDao = userDao;
    }

    public List<Message> findAll() {
        Iterable <Message> messages = this.messages.findAll();
        return (List<Message>) messages;
    }

    public Message save(Message message) {
        User user = userDao.getLoggedInUser();
        message.setSender(user);
        messages.save(message);
        return message;
    }

    public Message findOne(long id) {
        Message message = messages.findById(id).get();
        return message;
    }


    public Messages getMessages() {
        return messages;
    }

    public Message deleteMessage (long id){
        Message message = messages.findById(id).get();
        messages.delete(message);
        return deleteMessage(id);
    }

    public Message findById (Long id){
        return messages.findById(id).get();
    }



}
