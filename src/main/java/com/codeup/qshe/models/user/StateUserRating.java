package com.codeup.qshe.models.user;

import com.codeup.qshe.models.State;

import javax.persistence.*;

@Entity
@Table (name ="state_user_rating")
public class StateUserRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private State state;

    @OneToOne
    private StateMetric metric;

    @ManyToOne
    private User user;

    @Column (name="rating", nullable = false)
    private float rating;


    public StateUserRating (){}

    public StateUserRating(State state, User user, StateMetric metric, float rating) {
        this.state = state;
        this.user = user;
        this.metric = metric;
        this.rating = rating;
    }

    public StateUserRating(State state, StateMetric metric, float rating) {
        this.state = state;
        this.metric = metric;
        this.rating = rating;
    }



    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public StateMetric getMetric() { return metric; }

    public void setMetric(StateMetric metric) { this.metric = metric; }



}
