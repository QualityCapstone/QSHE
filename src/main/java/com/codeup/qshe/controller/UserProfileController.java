package com.codeup.qshe.controller;

import com.codeup.qshe.models.user.User;
import com.codeup.qshe.repositories.UserProfilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import com.codeup.qshe.repositories.Users;

import com.codeup.qshe.models.user.UserProfile;
import com.codeup.qshe.repositories.Users;
import com.codeup.qshe.services.user.SimpleSocialUsersDetailService;
import com.codeup.qshe.services.user.UserDetailsLoader;
import com.codeup.qshe.services.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserProfileController {
    private UserService userDao;
    private UserDetailsLoader userDetailsLoader;
    private UserProfilesRepository userProfilesRepository;

@Autowired
    public UserProfileController(UserDetailsLoader userDetailsLoader, UserService userDao, UserProfilesRepository userProfilesRepository) {
        this.userDetailsLoader = userDetailsLoader;
        this.userProfilesRepository = userProfilesRepository;
        this.userDao =userDao;
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


//        user.setUsername(user.getUsername());
//        System.out.println(userProfile.getUsername());
//        user.getProfile().setEmail(user.getProfile().getEmail());


        userDao.getUsers().updateProfile(existingUser.getProfile().getEmail(),existingUser.getUsername(),existingUser.getProfile().getFirstName(),existingUser.getProfile().getLastName(),existingUser.getId());
        userDao.getUsers().updateUser(existingUser.getUsername(),existingUser.getId());
        return "redirect:/profile";
    }
}
