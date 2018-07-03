package com.codeup.qshe.models;


import javax.persistence.*;
import java.time.LocalDate;

@Table
@Entity
public class StateEducation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private State state;

    private String origStateId;

    @Column
    private Long graduateCount;

    @Column
    private Integer year;

    public StateEducation() {}

    public StateEducation(State state, Long graduateCount, Integer year, String origStateId) {
        this.state = state;
        this.graduateCount = graduateCount;
        this.year = year;
        this.origStateId = origStateId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Long getGraduateCount() {
        return graduateCount;
    }

    public void setGraduateCount(Long graduateCount) {
        this.graduateCount = graduateCount;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getOrigStateId() {
        return origStateId;
    }

    public void setOrigStateId(String origStateId) {
        this.origStateId = origStateId;
    }
}
