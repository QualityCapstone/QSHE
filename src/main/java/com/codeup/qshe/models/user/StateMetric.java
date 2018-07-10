package com.codeup.qshe.models.user;

import javax.persistence.*;

@Entity
@Table
public class StateMetric {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;


    public StateMetric(){}

    public StateMetric(String name){
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
