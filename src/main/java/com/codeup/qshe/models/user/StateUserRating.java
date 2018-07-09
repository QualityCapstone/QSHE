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

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @Column (name="user_rating", nullable = false)
    private float userRate;


    public StateUserRating (){}

    public StateUserRating(State state, User user, float userRate) {
        this.state = state;
        this.user = user;
        this.userRate = userRate;
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

    public float getUserRate() {
        return userRate;
    }

    public void setUserRate(float userRate) {
        this.userRate = userRate;
    }



}
