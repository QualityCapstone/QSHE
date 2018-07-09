package com.codeup.qshe.services.user;

import com.codeup.qshe.models.user.Message;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.repositories.MessageRepository;
import com.codeup.qshe.repositories.Users;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagesService {
    private MessageRepository messageRepository;
    private Users users;
    private UserService userDao;


    public MessagesService(MessageRepository messageRepository, Users users, UserService userDao){
        this.messageRepository = messageRepository;
        this.users = users;
    }

    public MessageRepository getMessageRepository() {
        return messageRepository;
    }

    public List<Message> findAll() {
        Iterable <Message> messages = messageRepository.findAll();
        return (List<Message>) messages;
    }

    public Message save(Message message) {
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = users.findById(sessionUser.getId()).get();
        message.setSender(user);
        messageRepository.save(message);
        return message;
    }

    public Message findOne(long id) {
        Message message = messageRepository.findById(id).get();
        return message;
    }

//    public Message findBySender(Long id) {
//        Message message = messageRepository.findById(message.getSender())
//    }

    public Message deleteMessage (long id){
        Message message = messageRepository.findById(id).get();
        messageRepository.delete(message);
        return deleteMessage(id);
    }

    public Message findById (Long id){
        return messageRepository.findById(id).get();
    }


    public List<Message> findBySenderId(Long id) {
        User user = userDao.getLoggedInUser();
        List<Message> messages = messageRepository.findAllBySender(user);
        return messages;
    }
}
