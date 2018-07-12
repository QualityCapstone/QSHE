package com.codeup.qshe.controller.api;


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

}
