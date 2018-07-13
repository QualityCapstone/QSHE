package com.codeup.qshe.models.user;


import com.codeup.qshe.models.State;
import com.codeup.qshe.models.user.User;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import java.time.LocalDateTime;

@Entity
@Table
public class PostTopic {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @Column
    private String title;

    @Column
    private LocalDateTime createdAt = LocalDateTime.now();


    @Column
    private String topic;

    @ManyToOne
    private State state;

    public PostTopic() {

    }

    public PostTopic(User user, String title, State state) {
        this.user = user;
        this.title = title;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getTopic() {
        return topic;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}