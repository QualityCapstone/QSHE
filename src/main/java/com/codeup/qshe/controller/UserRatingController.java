package com.codeup.qshe.controller;

import com.codeup.qshe.models.user.UserRating;
import com.codeup.qshe.repositories.UserRatingRepository;
import com.codeup.qshe.repositories.Users;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserRatingController {
   private Users userDao;
   private UserRatingRepository userRatingRepository;


   public UserRatingController (Users userDao, UserRatingRepository userRatingRepository){
       this.userDao = userDao;
       this.userRatingRepository = userRatingRepository;
   }




    @PostMapping("/profile/rating")
    public float newUserRate(
            @RequestParam(name = "userId") long userId,
            @RequestParam(name= "stateId") long stateId,
            @RequestParam(name="userRating") float userRate){

       UserRating prevUserRating = userRatingRepository.findByUserId(userId);
       if (prevUserRating != null){
           userRatingRepository.delete(prevUserRating);
       }
       UserRating newUserRating = new UserRating(userId, stateId, userRate);
       userRatingRepository.save(newUserRating);

       return Float.parseFloat("redirect:/profile");

    }


}



