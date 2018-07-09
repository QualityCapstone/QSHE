package com.codeup.qshe.services;

import com.codeup.qshe.models.user.Message;
import com.codeup.qshe.models.user.StateUserRating;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.repositories.Staterepository;
import com.codeup.qshe.repositories.UserRatingRepository;
import com.codeup.qshe.repositories.Users;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateUserRatingService {
    private UserRatingRepository userRatingRepository;
    private Staterepository staterepository;
    private Users userDao;


    public StateUserRatingService(UserRatingRepository userRatingRepository, Staterepository staterepository, Users userDao) {
        this.userRatingRepository = userRatingRepository;
        this.staterepository = staterepository;
        this.userDao = userDao;
    }

    public List<StateUserRating> findAll() {
        Iterable <StateUserRating> userRatings = userRatingRepository.findAll();
        return (List<StateUserRating>) userRatings;
    }

    public StateUserRating save(StateUserRating userRating) {
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findById(sessionUser.getId()).get();
        userRating.setUser(user);
        userRatingRepository.save(userRating);
        return userRating;
    }

    public StateUserRating findOne(long id) {
        StateUserRating userRating = userRatingRepository.findById(id).get();
        return userRating;
    }

    public StateUserRating deleteUserRating (long id){
        StateUserRating userRating = userRatingRepository.findById(id).get();
        userRatingRepository.delete(userRating);
        return deleteUserRating(id);
    }

    public StateUserRating findById (Long id){
        return userRatingRepository.findById(id).get();
    }

}


