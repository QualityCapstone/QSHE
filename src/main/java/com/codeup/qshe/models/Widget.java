package com.codeup.qshe.models;

import javax.persistence.*;


@Entity
@Table
public class Widget {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long StateId;


    public Widget(){}

    public Widget(Long id, Long StateId){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStateId() {
        return StateId;
    }

    public void setStateId(Long stateId) {
        StateId = stateId;
    }

    public Widget(Long stateId) {
        StateId = stateId;
    }
}
