package com.codeup.qshe.services;


import com.codeup.qshe.models.user.StateMetric;
import com.codeup.qshe.models.user.StateUserRating;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StateUserRatingService {
    private UserRatings userRatings;
    private States states;
    private StateMetrics metrics;

    @Autowired
    public StateUserRatingService(UserRatings userRatings, States states, StateMetrics metrics) {
        this.userRatings = userRatings;
        this.states = states;
        this.metrics = metrics;
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


}


