package com.codeup.qshe.models.state;

import com.codeup.qshe.models.State;
import com.codeup.qshe.models.user.StateMetric;

import javax.persistence.*;
import java.util.HashMap;


@Entity
@Table
public class StateCalculatedRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private State state;

    @ManyToOne
    private StateMetric metric;

    @Column
    private float value;

    @Column
    private Integer ranking;

    public StateCalculatedRating() {}
    
    public StateCalculatedRating(State state, StateMetric metric, float value, Integer ranking) {
        this.state = state;
        this.metric = metric;
        this.value = value;
        this.ranking = ranking;
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

    public StateMetric getMetric() {
        return metric;
    }

    public void setMetric(StateMetric metrics) {
        this.metric = metrics;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }
}

