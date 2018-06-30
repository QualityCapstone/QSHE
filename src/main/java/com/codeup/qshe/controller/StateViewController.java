package com.codeup.qshe.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StateViewController {


    @GetMapping("/viewstate")
    public String viewState(Model model) { return "users/viewstate"; }
}
