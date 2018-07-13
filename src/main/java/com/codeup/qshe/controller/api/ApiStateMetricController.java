package com.codeup.qshe.controller.api;


import com.codeup.qshe.models.State;
import com.codeup.qshe.models.StateCrime;
import com.codeup.qshe.models.StateEducation;
import com.codeup.qshe.models.StatePopulation;
import com.codeup.qshe.models.state.StateAverageRanking;
import com.codeup.qshe.models.user.StateUserRating;
import com.codeup.qshe.services.StateMetricService;
import com.codeup.qshe.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("api/state")
public class ApiStateMetricController {
    private final StateService stateDao;
    private final StateMetricService metricDao;


    @Autowired
    public ApiStateMetricController(StateService stateDao, StateMetricService metricDao){
        this.stateDao = stateDao;
        this.metricDao = metricDao;
    }


    @RequestMapping(value="/ratings/average", method=GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity getAvgRatings() throws Exception {

        List<StateAverageRanking> ratings = metricDao.averageUserRatingsByState();

        return ResponseEntity.ok(ratings);
    }


    @RequestMapping(value="/ratings/average/{abbr}", method=GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity getAvgRatings(@PathVariable String abbr) throws Exception {

        State state  = stateDao.getStates().findByAbbr(abbr);

        StateAverageRanking rating = metricDao.averageUserRatingsByState(state);

        return ResponseEntity.ok(rating);
    }


    @RequestMapping(value="/crime/{abbr}", method=GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity getCrimeData(@PathVariable String abbr) throws Exception {
        State state  = stateDao.getStates().findByAbbr(abbr);


        List<StateCrime> crime = stateDao.getCrimes().findAllByState(state);

        return ResponseEntity.ok(crime);
    }


    @RequestMapping(value="/graduates/{abbr}", method=GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity getGradData(@PathVariable String abbr) throws Exception {
        State state  = stateDao.getStates().findByAbbr(abbr);

        List<StateEducation> educations = stateDao.getEducations().findAllByState(state);

        return ResponseEntity.ok(educations);
    }


    @RequestMapping(value="/population/{abbr}", method=GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity getPopulationData(@PathVariable String abbr) throws Exception {
        State state  = stateDao.getStates().findByAbbr(abbr);

        List<StatePopulation> populations = stateDao.getPopulations().findAllByState(state);

        return ResponseEntity.ok(populations);
    }






}
