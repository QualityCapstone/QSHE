package com.codeup.qshe.controller;

import com.codeup.qshe.models.State;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.repositories.UserProfiles;
import com.codeup.qshe.services.FlickrService;
import com.codeup.qshe.services.StateService;
import com.codeup.qshe.services.messages.MessagesService;
import com.flickr4java.flickr.FlickrException;
import org.springframework.beans.factory.annotation.Autowired;

import com.codeup.qshe.services.user.UserDetailsLoader;
import com.codeup.qshe.services.user.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;


@Controller
public class UserProfileController {
    private UserService userDao;
    private UserDetailsLoader userDetailsLoader;
    private UserProfiles userProfiles;
    private StateService stateDao;
    private MessagesService messageDao;


    @Value("${flickr-key}")
    private String apiKey;
    @Value("${flickr-secret}")
    private String sharedSecret;

@Autowired
    public UserProfileController(UserDetailsLoader userDetailsLoader,
                                 MessagesService messageDao,
                                 UserService userDao, UserProfiles userProfiles, StateService stateDao) {
        this.userDetailsLoader = userDetailsLoader;
        this.userProfiles = userProfiles;
        this.userDao =userDao;
        this.stateDao = stateDao;
        this.messageDao = messageDao;
    }

    @GetMapping("/users/displayprofile")
    public String displayProfile(Model model) throws FlickrException {
        User user = userDao.getLoggedInUser();

        model.addAttribute("conversations",
                messageDao.getMessages().findDistinctBySenderOrRecipientOrderByIdAsc(user, user));



        String userstate = user.getProfile().getUserState();
        State state = stateDao.getStates().findByName(userstate);

        FlickrService f = new FlickrService(apiKey, sharedSecret);

        model.addAttribute("photo", f.getPhoto(state.getName()));

        model.addAttribute("state", state);
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
