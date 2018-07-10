package com.codeup.qshe.services;

import com.codeup.qshe.models.user.StateMetric;
import com.codeup.qshe.models.user.StateUserRating;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.repositories.StateMetricRepository;

import com.codeup.qshe.repositories.Staterepository;
import com.codeup.qshe.repositories.UserRatings;
import com.codeup.qshe.repositories.Users;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateMetricService {
    private StateMetricRepository stateMetricRepository;
    private Users userDao;


   public StateMetricService(StateMetricRepository stateMetricRepository, Users userDao){
       this.stateMetricRepository = stateMetricRepository;
       this.userDao = userDao;
   }


    public StateMetricRepository getStateMetricRepository() {
        return stateMetricRepository;
    }

    public void setStateMetricRepository(StateMetricRepository stateMetricRepository) {
        this.stateMetricRepository = stateMetricRepository;
    }

    public Users getUserDao() {
        return userDao;
    }

    public void setUserDao(Users userDao) {
        this.userDao = userDao;
    }

    public List<StateMetric> findAll() {
        Iterable <StateMetric> stateMetrics = stateMetricRepository.findAll();
        return (List<StateMetric>) stateMetrics;
    }

    public StateMetric save(StateMetric stateMetric) {
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findById(sessionUser.getId()).get();
        stateMetric.setName("name");
        stateMetricRepository.save(stateMetric);
        return stateMetric;
    }

    public StateMetric findOne(long id) {
        StateMetric stateMetric = stateMetricRepository.findById(id).get();
        return stateMetric;
    }


    public StateMetric deleteRating (long id){
        StateMetric stateMetric = stateMetricRepository.findById(id).get();
        stateMetricRepository.delete(stateMetric);
        return deleteRating(id);
    }

    public StateMetric findById (Long id){
        return stateMetricRepository.findById(id).get();
    }

}
