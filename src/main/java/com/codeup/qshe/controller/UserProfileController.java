package com.codeup.qshe.controller;

import com.codeup.qshe.models.user.User;
import com.codeup.qshe.repositories.UserProfilesRepository;
import org.springframework.security.core.context.SecurityContextHolder;

import com.codeup.qshe.models.user.UserProfile;
import com.codeup.qshe.repositories.Users;
import com.codeup.qshe.services.user.SimpleSocialUsersDetailService;
import com.codeup.qshe.services.user.UserDetailsLoader;
import com.codeup.qshe.services.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class UserProfileController {

    private UserDetailsLoader userDetailsLoader;
    private UserProfilesRepository userProfilesRepository;

    public UserProfileController(UserDetailsLoader userDetailsLoader, UserProfilesRepository userProfilesRepository) {
        this.userDetailsLoader = userDetailsLoader;
        this.userProfilesRepository = userProfilesRepository;
    }

    @RequestMapping("/user/{id}/edit")
    public String update(@PathVariable Long id,  UserProfile userProfile){

        UserProfile existingProfile = userDetailsLoader.getProfile(id);

        existingProfile.setEmail(userProfile.getEmail());


        userProfilesRepository.save(existingProfile);
        return "redirect:/profile";
    }
}
