package com.codeup.qshe.services.messages;

import com.codeup.qshe.models.user.Message;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.repositories.MessageRepository;
import com.codeup.qshe.repositories.Users;
import com.codeup.qshe.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class messageService {
    private MessageRepository messageRepository;
    private UserService userDao;

    public messageService(MessageRepository messageRepository, UserService userDao) {
        this.messageRepository = messageRepository;
       this.userDao = userDao;


    }

    @Autowired
    public List<Message> findAll() {
        Iterable<Message> messages = messageRepository.findAll();

        return (List<Message>) messages;
    }

    public MessageRepository getMessageRepository(){
        return messageRepository;
    }


    public List<Message> getRelated(Long senderId, Long recipientId){

        List<Message> messages = (List<Message>) messageRepository.getRelated(senderId, recipientId);
        return  messages;
    }

    public List<Message>  findBySenderId(Long id) {
        User user = userDao.getLoggedInUser();
        List<Message> messages = messageRepository.findAllBySender(user);
    return messages;
    }


//    public Message findBySenderId(Long id){
//        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//         id = sessionUser.getId();
//
//         Message message = messageRepository.findById(Message.getSender().getId());
//
//        return message;
//    }
//
//    public Message findByRecipientId(){
//
//        return Message;
//    }

}