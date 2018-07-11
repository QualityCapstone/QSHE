package com.codeup.qshe.services;

import com.codeup.qshe.models.State;
import com.codeup.qshe.models.user.StateMetric;
import com.codeup.qshe.models.user.StateUserRating;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.repositories.StateMetrics;

import com.codeup.qshe.repositories.States;
import com.codeup.qshe.repositories.UserRatings;
import com.codeup.qshe.repositories.Users;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class StateMetricService {
    private StateMetrics stateMetrics;
    private States states;
    private UserRatings ratings;


   public StateMetricService(StateMetrics stateMetrics, States states, UserRatings ratings){
       this.stateMetrics = stateMetrics;
       this.states = states;
       this.ratings = ratings;

   }


    public StateMetrics getStateMetrics() {
        return stateMetrics;
    }

    public List<StateMetric> findAll() {
        Iterable <StateMetric> stateMetrics = this.stateMetrics.findAll();
        return (List<StateMetric>) stateMetrics;
    }

    public StateMetric save(StateMetric stateMetric) {
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        stateMetric.setName("name");
        stateMetrics.save(stateMetric);
        return stateMetric;
    }

    public States getStates() {
        return states;
    }

    public UserRatings getRatings() {
        return ratings;
    }

    public StateMetric findOne(long id) {
        StateMetric stateMetric = stateMetrics.findById(id).get();
        return stateMetric;
    }


    public StateMetric deleteRating (long id){
        StateMetric stateMetric = stateMetrics.findById(id).get();
        stateMetrics.delete(stateMetric);
        return deleteRating(id);
    }


    // Returns all states average User ratings for the associated
    // ranking
    public HashMap<State, List<StateUserRating>> averageUserRatingsByState() {

       HashMap<State, List<StateUserRating>> map = new HashMap<>();
       List<State> states = getStates().findAll();

       for(State state : states) {
           List<StateUserRating> avgRatings = new ArrayList<>();
           List<StateMetric> metrics = getStateMetrics().findAll();

           for(StateMetric metric : metrics) {
               Float  average = getRatings().avgRatingByStateAndMetric(state, metric );
               avgRatings.add(new StateUserRating(state,metric,average));
           }

           map.put(state, avgRatings);
       }

       return map;
    }


//    public HashMap<State, Float> overallAverageRatingByState() {
//
//    }

    public StateMetric findById (Long id){
        return stateMetrics.findById(id).get();
    }

}
