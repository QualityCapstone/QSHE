package com.codeup.qshe.services;


import com.codeup.qshe.models.user.StateUserRating;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.repositories.Staterepository;
import com.codeup.qshe.repositories.UserRatings;
import com.codeup.qshe.repositories.Users;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StateUserRatingService {
    private UserRatings userRatings;
    private Staterepository staterepository;
    private Users userDao;


    public StateUserRatingService(UserRatings userRatings, Staterepository staterepository, Users userDao) {
        this.userRatings = userRatings;
        this.staterepository = staterepository;
        this.userDao = userDao;
    }


    public List<StateUserRating> findAll() {
        Iterable <StateUserRating> stateUserRatings = userRatings.findAll();
        return (List<StateUserRating>) stateUserRatings;
    }

    public StateUserRating save(StateUserRating stateUserRating) {
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findById(sessionUser.getId()).get();
        stateUserRating.setUser(user);
        userRatings.save(stateUserRating);
        return stateUserRating;
    }

    public StateUserRating findOne(long id) {
        StateUserRating stateUserRating = userRatings.findById(id).get();
        return stateUserRating;
    }


    public StateUserRating deleteRating (long id){
        StateUserRating stateUserRating = userRatings.findById(id).get();
        userRatings.delete(stateUserRating);
        return deleteRating(id);
    }

    public StateUserRating findById (Long id){
        return userRatings.findById(id).get();
    }


}


