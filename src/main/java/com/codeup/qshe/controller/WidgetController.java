package com.codeup.qshe.controller;

import com.codeup.qshe.models.State;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.services.StateService;
import com.codeup.qshe.services.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WidgetController {
    private UserService userDao;
    private StateService stateService;

    public WidgetController(UserService userDao, StateService stateService){
        this.userDao = userDao;
        this.stateService = stateService;
    }


    @GetMapping("/widget")
    public String viewForum(Model view){
    List<State> states = stateService.findAll();
    view.addAttribute("states", states);
    return "widget/all";

    }

}


