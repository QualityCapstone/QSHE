package com.codeup.qshe.services;

import com.codeup.qshe.models.State;
import com.codeup.qshe.models.user.StateMetric;
import com.codeup.qshe.repositories.UserRatingRepository;
import com.codeup.qshe.repositories.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateMetricService {
    private UserRatingRepository userRatingRepository;
    private Users userDao;


    public UserRatingRepository getUserRatingRepository() {
        return userRatingRepository;
    }

    public void setUserRatingRepository(UserRatingRepository userRatingRepository) {
        this.userRatingRepository = userRatingRepository;
    }

    public Users getUserDao() {
        return userDao;
    }

    public void setUserDao(Users userDao) {
        this.userDao = userDao;
    }

    public StateMetricService(UserRatingRepository userRatingRepository, Users userDao){
        this.userRatingRepository = userRatingRepository;
        this.userDao = userDao;
    }

//    public List<StateMetricService> findAll(){
//        Iterable<StateMetricService> stateMetrics = .findAll();
//        return (List<StateMetricService>) stateMetrics;
//    }

//    public State findByName (String metricsName) {
//        StateMetricService stateMetricService = userRatingRepository.findByName();
//        return metricsName;
//    }
//




}
