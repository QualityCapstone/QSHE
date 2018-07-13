package com.codeup.qshe.controller;

import com.codeup.qshe.models.State;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.models.user.UserProfile;
import com.codeup.qshe.models.user.UserWithRoles;
import com.codeup.qshe.repositories.Roles;
import com.codeup.qshe.repositories.UserProfiles;
import com.codeup.qshe.services.FlickrService;
import com.codeup.qshe.services.StateService;
import com.codeup.qshe.services.messages.MessagesService;
import com.codeup.qshe.services.user.UserDetailsLoader;
import com.codeup.qshe.services.user.UserService;
import com.flickr4java.flickr.FlickrException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
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
    private UserProfiles userProfiles;
    private MessagesService messageDao;
    private StateService stateDao;


    @Value("${flickr-key}")
    private String apiKey;
    @Value("${flickr-secret}")
    private String sharedSecret;


    @Autowired
    public UserController(UserService userDao, PasswordEncoder passwordEncoder, Roles roles,
                          MessagesService messageDao, StateService stateDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.roles = roles;
        this.messageDao = messageDao;
        this.stateDao  = stateDao;

    }

    @GetMapping("/sign-up")
        public String showSignupForm(Model model) throws FlickrException {

            FlickrService f = new FlickrService(apiKey, sharedSecret);
            State state =  stateDao.getStates().getRandom();

            model.addAttribute("photo", f.getPhoto(state.getName()));
            model.addAttribute("user", new User());
            return "users/sign-up";

    }

    @PostMapping("/sign-up")
    public String saveUser(@Valid User user, Errors validation, Model model, @ModelAttribute UserProfile profile) {

        if(validation.hasErrors()){
            model.addAttribute("errors", validation);
            model.addAttribute("user", user);
            System.out.println(user.getUsername());

            return "redirect:/sign-up";
        }

        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        user.setCreatedAt(LocalDateTime.now());
        user.getProfile().setUsername(user.getUsername());
        userDao.getUsers().save(user);
        userDao.getUsers().addDefaultRole(user.getId());

        authenticate(user);

        return "redirect:/users/rating";
    }



    @GetMapping("/editprofile")
    public String loadProfile(Model model) {
        User user = userDao.getLoggedInUser();

        user = userDao.getUsers().findByUsername(user.getUsername());
        State state = stateDao.getStates().findByName(user.getProfile().getUserState());
        model.addAttribute("state", state);
        model.addAttribute("user", user);

        return "users/editprofile";
    }



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


}
