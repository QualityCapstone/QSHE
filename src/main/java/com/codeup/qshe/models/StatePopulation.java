package com.codeup.qshe.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class StatePopulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private State state;

    @Column
    private Long population;



    @Column
    private LocalDate dateDataCreated;


    public StatePopulation() {}

    public StatePopulation(State state, Long population, LocalDate dateDataCreated) {
        this.state = state;
        this.population = population;
        this.dateDataCreated = dateDataCreated;
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

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }


    public LocalDate getDateDataCreated() {
        return dateDataCreated;
    }

    public void setDateDataCreated(LocalDate dateDataCreated) {
        this.dateDataCreated = dateDataCreated;
    }


    @Override
    public String toString() {
        return "StatePopulation{" +
                "id=" + id +
                ", state=" + state +
                ", population=" + population +
                ", dateDataCreated=" + dateDataCreated +
                '}';
    }
}
