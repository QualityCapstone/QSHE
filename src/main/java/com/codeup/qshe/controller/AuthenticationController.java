package com.codeup.qshe.controller;

import com.codeup.qshe.models.user.User;
import com.codeup.qshe.services.FlickrService;
import com.codeup.qshe.services.user.SocialControllerService;
import com.codeup.qshe.services.user.UserService;
import com.flickr4java.flickr.FlickrException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@Controller
public class AuthenticationController {
    private SocialControllerService util;
    private UserService userDao;


    @Value("${flickr-key}")
    private String apiKey;
    @Value("${flickr-secret}")
    private String sharedSecret;

    @Autowired
    public AuthenticationController(SocialControllerService util) {
        this.util = util;
    }


    @GetMapping("/login")
    public String showLoginForm(HttpServletRequest request, Principal currentUser, Model model) throws FlickrException {
        Authentication token = SecurityContextHolder.getContext().getAuthentication();
        if (!(token instanceof AnonymousAuthenticationToken)) {
            return "redirect:/users/displayprofile";
        }


        User user = new User();


        FlickrService f = new FlickrService(apiKey, sharedSecret);
        model.addAttribute("photo", f.getPhoto("Texas"));
        util.setModel(request, currentUser, model);
        model.addAttribute("user",user);




        // not logged in
        if (token instanceof AnonymousAuthenticationToken) {
            return "users/login";
        }

        // redirect to home page
        return String.format("redirect:%s", "/");

    }




}
