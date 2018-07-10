package com.codeup.qshe.controller;


import com.codeup.qshe.models.State;
import com.codeup.qshe.models.user.StateMetric;
import com.codeup.qshe.models.user.StateUserRating;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.services.StateUserRatingService;
import com.codeup.qshe.services.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserRatingController {
    private final StateUserRatingService ratingDao;
    private final UserService userDao;

    public UserRatingController(StateUserRatingService ratingDao, UserService userDao) {
        this.ratingDao = ratingDao;
        this.userDao = userDao;
    }


    @GetMapping("/users/rating")
    public String viewUserRating(Model view) {
        view.addAttribute("userRatings", ratingDao.getUserRatings().findAll());
        view.addAttribute("newRating", new StateUserRating());

        return "/users/rating";
    }


    @PostMapping("/users/rating")
    public String saveUserRate(@RequestParam HashMap<String, String> formData) {

        User user = userDao.getLoggedInUser();
        State state = ratingDao.getStates().findByName(user.getProfile().getUserState());

        ratingDao.getUserRatings().deleteByStateAndUser(state, user);

        for (Map.Entry<String, String> entry : formData.entrySet()) {

            String key = entry.getKey().toString();
            String cap = key.substring(0, 1).toUpperCase() + key.substring(1);

            String value = entry.getValue();

//            System.out.println("key, " + key + " value " + value);

            //Case sensitive
            StateMetric metric = ratingDao.getMetrics().findByName(cap);

            if(metric != null) {
                ratingDao.getUserRatings().save(new StateUserRating(state, user, metric, Float.parseFloat(value)));
            }

        }


        return "/users/rating";

    }

}



