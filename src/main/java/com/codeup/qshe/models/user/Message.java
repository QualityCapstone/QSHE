package com.codeup.qshe.models.user;

import javax.persistence.*;
import javax.persistence.GeneratedValue;

    @Entity
    @Table
    public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User recipient;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;


    public Message(){}


    public Message(User sender, User recipient, String message) {
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
    }


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public User getSender() {
            return sender;
        }

        public void setSender(User sender) {
            this.sender = sender;
        }

        public User getRecipient() {
            return recipient;
        }

        public void setRecipient(User recipient) {
            this.recipient = recipient;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }