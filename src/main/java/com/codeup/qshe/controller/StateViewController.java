package com.codeup.qshe.controller;

import com.codeup.qshe.models.user.State;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.repositories.Users;
import com.codeup.qshe.services.user.SocialControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class StateViewController {
    private Users users;
    private State state;



    @GetMapping("/showState")
    public String showStateView (Model model) {

        model.addAttribute("state", new State());

        return "states/showState";
    }
}
