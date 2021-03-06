package com.codeup.qshe.services;

import com.codeup.qshe.models.State;
import com.codeup.qshe.models.StateEducation;
import com.codeup.qshe.models.state.StateAverageRanking;
import com.codeup.qshe.models.state.StateCalculatedRating;
import com.codeup.qshe.models.user.StateMetric;
import com.codeup.qshe.models.user.StateUserRating;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.repositories.*;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
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
    private StateCalculatedRatings calculated;
    private StateEducations educations;

    @Autowired
   public StateMetricService(StateMetrics stateMetrics, States states,
                             UserRatings ratings, StateCalculatedRatings calculated,
                        StateEducations educations
    ){
       this.stateMetrics = stateMetrics;
       this.states = states;
       this.ratings = ratings;
       this.calculated = calculated;
       this.educations = educations;

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
    public List<StateAverageRanking> averageUserRatingsByState() {

       List<State> states = getStates().findAll();
       List<StateMetric> metrics = getStateMetrics().findAll();

       List<StateAverageRanking> avgRankings = new ArrayList<>();

       for(State state : states) {

           StateAverageRanking rank = new StateAverageRanking(state);

           for(StateMetric metric : metrics) {
               Float  average = getRatings().avgRatingByStateAndMetric(state, metric);
                rank.addMetric(metric,average);
           }

           avgRankings.add(rank);

       }

       return avgRankings;
    }


    public StateAverageRanking averageUserRatingsByState(State state) {

        List<StateMetric> metrics = getStateMetrics().findAll();
        StateAverageRanking rank = new StateAverageRanking(state);

        for(StateMetric metric : metrics) {
            Float  average = getRatings().avgRatingByStateAndMetric(state, metric);
            rank.addMetric(metric,average);
        }

        return rank;
    }


    public StateAverageRanking calculatedRatingsByState(State state) {

        List<StateMetric> metrics = getStateMetrics().findAll();
        StateAverageRanking rank = new StateAverageRanking(state);

        for(StateMetric metric : metrics) {
            Float  average = getCalculated().findByStateAndMetric(state, metric).getValue();
            rank.addMetric(metric,average);
        }

        return rank;
    }



    public StateCalculatedRatings getCalculated() {
        return calculated;
    }

    public HashMap<String, String> getMetricRankings(State state) {


      List<StateCalculatedRating> calc = getCalculated().findAllByState(state);

        HashMap<String, String> easyMap = new HashMap<>();


        for (StateCalculatedRating c : calc) {
            StateMetric key = c.getMetric();
            Integer value = c.getRanking();

            easyMap.put(key.getName(), ordinal(value));
        }

        return easyMap;
    }



    public HashMap<String, String> generateUniqueMetrics(State state) {

        HashMap<String, String> easyMap = new HashMap<>();

        Faker faker = new Faker();


        // name, value
        // overall_crime_increase
        easyMap.put("overall_crime_increase", faker.random().nextInt(2,8).toString());
        // percent_population_increase
        easyMap.put("percent_population_increase", getStateMetrics().percentPopulationIncreaseByState(state).toString());
        // year_over_year_population_growth
        easyMap.put("year_over_year_population_growth", getStateMetrics().percentPopulationIncreaseByState(state).toString());
        // graduates_increase
        easyMap.put("graduates_increase", getEducations().percentIncreaseByState(state).toString());
        // graduates_total
        easyMap.put("graduates_total", getEducations().getTotalGraduatesByState(state).toString());
        // total_health_facilities_since_year
        easyMap.put("total_health_facilities_since_year", faker.random().nextInt(500,50000).toString());





       return easyMap;
    }


    public StateEducations getEducations() {
        return educations;
    }

    private static String ordinal(int i) {
        String[] sufixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
        switch (i % 100) {
            case 11:
            case 12:
            case 13:
                return i + "th";
            default:
                return i + sufixes[i % 10];

        }
    }


//    public HashMap<State, Float> overallAverageRatingByState() {
//
//    }

    public StateMetric findById (Long id){
        return stateMetrics.findById(id).get();
    }

}
