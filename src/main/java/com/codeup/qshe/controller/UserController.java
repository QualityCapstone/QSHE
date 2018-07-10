package com.codeup.qshe.controller;

import com.codeup.qshe.models.user.Message;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.models.user.UserProfile;
import com.codeup.qshe.models.user.UserWithRoles;
import com.codeup.qshe.repositories.Roles;
import com.codeup.qshe.repositories.UserProfilesRepository;
import com.codeup.qshe.repositories.Users;
import com.codeup.qshe.services.user.SimpleSocialUsersDetailService;
import com.codeup.qshe.services.user.UserDetailsLoader;
import com.codeup.qshe.services.user.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class UserController {
    private UserDetailsLoader userDetailsLoader;
    private UserService userDao;
    private PasswordEncoder passwordEncoder;
    private Roles roles;
    private UserProfilesRepository userProfilesRepository;




    public UserController(UserService userDao, PasswordEncoder passwordEncoder, Roles roles) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.roles = roles;

    }


    @GetMapping("/sign-up")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "users/sign-up";

    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user, @ModelAttribute UserProfile profile) {

        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        user.setCreatedAt(LocalDateTime.now());
        user.getProfile().setUsername(user.getUsername());

        userDao.getUsers().save(user);
        userDao.getUsers().addDefaultRole(user.getId());

        authenticate(user);
        return "redirect:/profile";
    }



    @GetMapping("/profile")
    public String loadProfile(Model model) {
        User user = userDao.getLoggedInUser();
        model.addAttribute("user", user);
        return "users/profile";
    }




//     @GetMapping("/news")
//     public String newsApi() {
//         return "users/newstest";
//     }


    @GetMapping("/users")
    public String viewAllUsers(Model view) {
        List<User> users = userDao.findAll();
        view.addAttribute("users", users);
        return "users/all";
    }


    private void authenticate(User user) {
        UserDetails userDetails = new UserWithRoles(user, roles.ofUserWith(user.getUsername()));
        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(auth);
    }

//    @PostMapping("/user/{id}/edit")
//    public String updateUser(@PathVariable Long id, User user) {
//
//        User currentUser = users.getOne(id);
//
////        existingProfile.setEmail(userProfile.getEmail());
//
//        currentUser.setUsername(user.getUsername());
//
//        users.save(currentUser);
//        return "redirect:/profile";
//    }



}
