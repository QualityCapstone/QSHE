package com.codeup.qshe.services;

import com.codeup.qshe.models.user.StateMetric;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.repositories.StateMetrics;

import com.codeup.qshe.repositories.Users;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateMetricService {
    private StateMetrics stateMetrics;
    private Users userDao;


   public StateMetricService(StateMetrics stateMetrics, Users userDao){
       this.stateMetrics = stateMetrics;
       this.userDao = userDao;
   }


    public StateMetrics getStateMetrics() {
        return stateMetrics;
    }

    public void setStateMetrics(StateMetrics stateMetrics) {
        this.stateMetrics = stateMetrics;
    }

    public Users getUserDao() {
        return userDao;
    }

    public void setUserDao(Users userDao) {
        this.userDao = userDao;
    }

    public List<StateMetric> findAll() {
        Iterable <StateMetric> stateMetrics = this.stateMetrics.findAll();
        return (List<StateMetric>) stateMetrics;
    }

    public StateMetric save(StateMetric stateMetric) {
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findById(sessionUser.getId()).get();
        stateMetric.setName("name");
        stateMetrics.save(stateMetric);
        return stateMetric;
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

    public StateMetric findById (Long id){
        return stateMetrics.findById(id).get();
    }

}
