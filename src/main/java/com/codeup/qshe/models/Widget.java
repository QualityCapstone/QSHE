package com.codeup.qshe.models;

import javax.persistence.*;

@Entity
@Table (name="Widgets")
public class Widget {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;


    public Widget(){}

    public Widget( String name, Long id){
        this.name = name;
        this.id = id;
    }

}
