package com.codeup.qshe.services.messages;

import com.codeup.qshe.models.user.Message;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class messageService {
    private MessageRepository messageRepository;

    public messageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;


    }

    @Autowired
    public List<Message> findAll() {
        Iterable<Message> messages = messageRepository.findAll();

        return (List<Message>) messages;
    }

    public MessageRepository getMessageRepository(){
        return messageRepository;
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