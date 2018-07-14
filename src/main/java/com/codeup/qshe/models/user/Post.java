package com.codeup.qshe.models.user;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class Post {

    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne(cascade = CascadeType.ALL)
    private PostTopic topic;

    @ManyToOne
    private User user;

    @Column
    private String message;

    @Column
    private LocalDateTime createdAt = LocalDateTime.now();


    public Post() {}

    public Post(PostTopic topic, User user, String message) {
        this.topic = topic;
        this.user = user;
        this.message = message;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PostTopic getTopic() {
        return topic;
    }

    public void setTopic(PostTopic topic) {
        this.topic = topic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
