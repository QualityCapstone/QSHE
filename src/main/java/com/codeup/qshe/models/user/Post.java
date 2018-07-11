package com.codeup.qshe.models.user;


import com.codeup.qshe.models.user.User;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.persistence.GeneratedValue;

@Entity
@Table
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @Column
    private String title;

    @Column(name = "body", columnDefinition = "TEXT")
    private String body;

    @Column
    private String topic;

    @Column
    private Long stateId;

    public Post() {

    }

    public Post(User user) {
        this.user = user;
    }

    public Post(User user, String title) {
        this.user = user;
        this.title = title;
    }

    public Post(User user, String title, String body) {
        this.user = user;
        this.title = title;
        this.body = body;
    }

    public Post(User user, String title, String body, String topic, Long stateId) {
        this.user = user;
        this.title = title;
        this.body = body;
        this.topic = topic;
        this.stateId = stateId;
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

    public String getBody() {
        return body;
    }

    public String getTopic() {
        return topic;
    }

    public Long getStateId() {
        return stateId;
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

    public void setBody(String body) {
        this.body = body;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }
}
