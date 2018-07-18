package com.codeup.qshe.services;



import com.codeup.qshe.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StateUserRatingService {
    private UserRatings userRatings;
    private States states;
    private StateMetrics metrics;
    private StateCalculatedRatings calculated;

    @Autowired
    public StateUserRatingService(UserRatings userRatings, States states, StateMetrics metrics, StateCalculatedRatings calculated) {
        this.userRatings = userRatings;
        this.states = states;
        this.metrics = metrics;
        this.calculated = calculated;
    }

    public UserRatings getUserRatings() {
        return userRatings;
    }

    public States getStates() {
        return states;
    }

    public StateMetrics getMetrics() {
        return metrics;
    }

    public StateCalculatedRatings getCalculated() { return calculated; }

}


