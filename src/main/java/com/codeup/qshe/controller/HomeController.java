package com.codeup.qshe.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    public HomeController() {

    }

    @GetMapping("/")
    public String displayMainPage() {
        return "/index";
    }

    @GetMapping("/dashboard")
    public String viewDashboard(Model model) {

        return "user";
    }

    @GetMapping("/map")
    public String displayMap(){
        return "/Map/map";
    }
}
