package com.codeup.qshe.controller;


import com.codeup.qshe.models.State;
import com.codeup.qshe.models.user.StateUserRating;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.services.StateUserRatingService;
import com.codeup.qshe.services.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserRatingController {
   private final StateUserRatingService stateUserRatingService;
   private final UserService userDao;

   public UserRatingController (StateUserRatingService stateUserRatingService, UserService userDao){
       this.stateUserRatingService = stateUserRatingService;
       this.userDao = userDao;
   }

//   --------------- PRIMER INTENTO ------------------------------------

//    @GetMapping ("/profile/rating")
//    public String showUserRate (Model view) {
//       User usersession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//       User user = userDao.findById(usersession.getId()).get();
//       if(userRatingRepository.findByUserId(user.getId()) != null){
//           StateUserRating userRating = userRatingRepository.findByUserId(user.getId());
//           view.addAttribute("user", user);
//           view.addAttribute("userRating", userRating);
//       } else {
//           StateUserRating userRating = new StateUserRating();
//           view.addAttribute("user", user);
//           view.addAttribute("userRating", userRating);
//       }
//
//       return "/profile/rating";
//    }


//    @PostMapping("/profile/rating")
//    public String newUserRate(
//            @RequestParam(name = "userId") User userId,
//            @RequestParam(name= "stateId") State stateId,
//            @RequestParam(name="userRating") float userRate){
//
//       StateUserRating prevUserRating = userRatingRepository.findByUserId(userId);
//       if (prevUserRating != null){
//           userRatingRepository.delete(prevUserRating);
//       }
//       StateUserRating newUserRating = new StateUserRating(stateId, userId, userRate);
//       userRatingRepository.save(newUserRating);
//       return "redirect:/profile/rating";
//
//    }

// ------------------ SEGUNDO INTENTO ---------------------

    @GetMapping("/users/rating")
    public String viewUserRating(Model view) {
        System.out.println("hello");
        List<StateUserRating> userRatings = stateUserRatingService.findAll();
        view.addAttribute("userRatings", userRatings);
        view.addAttribute("newRating", new StateUserRating());

        return "/users/rating";
    }


//    @PostMapping("/users/rating")
//    public String saveUserRate (@RequestParam(name = "userRate") float userRate){
//
//        StateUserRating userRating = new StateUserRating();
//        userRating.setRating(userRate);
//        User user = userDao.getLoggedInUser();
//        userRating.setUser(user);
//        stateUserRatingService.save(userRating);
//        return "redirect:/users/rating";
//    }


    @PostMapping("/users/rating")
    public String saveUserRate (@ModelAttribute("hdrating") StateUserRating stateUserRating,
    @RequestParam("hdrating")float rating, User user, State state){
       user = userDao.getLoggedInUser();
       stateUserRating.setUser(user);
       stateUserRating.setState(state);
       stateUserRating.setRating(rating);
       stateUserRatingService.save(stateUserRating);
       return "redirect:/users/rating/" + stateUserRating.getState().getId() + stateUserRating.getMetric();

    }



//     -------------- Tercer Intento ------------------------------

//    @PostMapping("/users/rating")
//    public String saveUserRating(@ModelAttribute StateUserRating userRating
//    ){
//       userRating.setUser(userDao.findById(id));
//       stateUserRatingService.save(userRating);
//
//       return"redirect:/users/rating";
//    }

}



