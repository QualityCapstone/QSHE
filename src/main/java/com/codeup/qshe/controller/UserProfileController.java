package com.codeup.qshe.controller;

import com.codeup.qshe.models.user.User;
import com.codeup.qshe.repositories.UserProfilesRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import com.codeup.qshe.repositories.Users;

import com.codeup.qshe.models.user.UserProfile;
import com.codeup.qshe.repositories.Users;
import com.codeup.qshe.services.user.SimpleSocialUsersDetailService;
import com.codeup.qshe.services.user.UserDetailsLoader;
import com.codeup.qshe.services.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class UserProfileController {
    private Users users;
    private UserDetailsLoader userDetailsLoader;
    private UserProfilesRepository userProfilesRepository;


    public UserProfileController(UserDetailsLoader userDetailsLoader, UserProfilesRepository userProfilesRepository, Users users) {
        this.userDetailsLoader = userDetailsLoader;
        this.userProfilesRepository = userProfilesRepository;
        this.users =users;
    }

    @RequestMapping("/user/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute User user){

//        User existingUser = userDetailsLoader.getProfile(id);
////        User currentUser = users.getOne(id);
//



//        user.setUsername(user.getUsername());
//        System.out.println(userProfile.getUsername());
//        user.getProfile().setEmail(user.getProfile().getEmail());


//        users.save(currentUser);
        users.save(user);
        return "redirect:/profile";
    }
}
