package com.codeup.qshe.models;

import com.codeup.qshe.models.user.Message;

import javax.persistence.*;
import java.util.List;


@Entity
@Table
public class Widget {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long stateId;

    @OneToMany (cascade = CascadeType.ALL)
    private List<Message> posts;


    public Widget(){}

    public Widget(Long id, Long stateId, List<Message> posts){
    this.id = id;
    this.stateId = stateId;
    this.posts = posts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public List <Message> getPosts() {
        return posts;
    }

    public void setPosts(List <Message> posts) {
        this.posts = posts;
    }
}
