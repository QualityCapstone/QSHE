package com.codeup.qshe.models;

import com.codeup.qshe.models.user.Message;

import javax.persistence.*;
import java.util.List;


@Entity
@Table
public class Widget {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private long stateId;

    @OneToMany (cascade = CascadeType.ALL)
    private List<Message> posts;


    public Widget(){}

    public Widget(long id, long stateId, List<Message> posts){
    this.id = id;
    this.stateId = stateId;
    this.posts = posts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStateId() {
        return stateId;
    }

    public void setStateId(long stateId) {
        this.stateId = stateId;
    }

    public List <Message> getPosts() {
        return posts;
    }

    public void setPosts(List <Message> posts) {
        this.posts = posts;
    }


}
