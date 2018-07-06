package com.codeup.qshe.controller;

import com.codeup.qshe.models.user.User;
import com.codeup.qshe.models.user.UserRating;
import com.codeup.qshe.repositories.UserRatingRepository;
import com.codeup.qshe.repositories.Users;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping ("/profile/rating")
    public String showUserRate (Model view) {
       User usersession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       User user = userDao.findById(usersession.getId()).get();
       if(userRatingRepository.findByUserId(user.getId()) != null){
           UserRating userRating = userRatingRepository.findByUserId(user.getId());
           view.addAttribute("user", user);
           view.addAttribute("userRating", userRating);
       } else {
           UserRating userRating = new UserRating();
           view.addAttribute("user", user);
           view.addAttribute("userRating", userRating);
       }

       return "profile/rating";
    }


    @PostMapping("/profile/rating")
    public String newUserRate(
            @RequestParam(name = "userId") long userId,
            @RequestParam(name= "stateId") long stateId,
            @RequestParam(name="userRating") float userRate){

       UserRating prevUserRating = userRatingRepository.findByUserId(userId);
       if (prevUserRating != null){
           userRatingRepository.delete(prevUserRating);
       }
       UserRating newUserRating = new UserRating(userId, stateId, userRate);
       userRatingRepository.save(newUserRating);
       return "redirect:/profile/rating";

    }


}



