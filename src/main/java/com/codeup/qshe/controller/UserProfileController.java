package com.codeup.qshe.controller;

import com.codeup.qshe.models.State;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.repositories.UserProfiles;
import org.springframework.beans.factory.annotation.Autowired;

import com.codeup.qshe.services.user.UserDetailsLoader;
import com.codeup.qshe.services.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;


@Controller
public class UserProfileController {
    private UserService userDao;
    private UserDetailsLoader userDetailsLoader;
    private UserProfiles userProfiles;

@Autowired
    public UserProfileController(UserDetailsLoader userDetailsLoader, UserService userDao, UserProfiles userProfiles) {
        this.userDetailsLoader = userDetailsLoader;
        this.userProfiles = userProfiles;
        this.userDao =userDao;
    }

    @GetMapping("/users/displayprofile")
    public String displayProfile(Model model) {
        User user = userDao.getLoggedInUser();
        String userstate = user.getProfile().getUserState();
        model.addAttribute("state", userstate);
        model.addAttribute("user", user);

    return "users/displayprofile";
    }



    @PostMapping("/user/{id}/edit")
    public String update(Long id, @ModelAttribute User user){

        User existingUser = userDao.getLoggedInUser();
//        User currentUser = users.getOne(id);


        System.out.println(existingUser.toString());

        existingUser.setUsername(user.getUsername());
        existingUser.getProfile().setEmail(user.getProfile().getEmail());
        existingUser.getProfile().setFirstName(user.getProfile().getFirstName());
        existingUser.getProfile().setLastName(user.getProfile().getLastName());
        existingUser.getProfile().setName(user.getProfile().getName());


        userDao.getUsers().updateProfile(existingUser.getProfile().getEmail(),existingUser.getUsername(),existingUser.getProfile().getFirstName(),existingUser.getProfile().getLastName(),existingUser.getProfile().getName(),existingUser.getId());
        userDao.getUsers().updateUser(existingUser.getUsername(),existingUser.getId());
        return "redirect:/users/displayprofile";
    }

    public String findByUsernameLike(String searchString){

    return "";
    }
}
