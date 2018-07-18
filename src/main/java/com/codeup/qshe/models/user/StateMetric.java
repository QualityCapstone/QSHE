package com.codeup.qshe.models.user;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StateMetric that = (StateMetric) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
