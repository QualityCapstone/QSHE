package com.codeup.qshe.controller;

//
//import com.codeup.qshe.models.user.State;
//import com.codeup.qshe.repositories.States;
//import com.codeup.qshe.services.user.StateService;
import com.codeup.qshe.models.State;
import com.codeup.qshe.services.StateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
 public class StateController {
  private final StateService stateService;



      public StateController(StateService stateService){
          this.stateService = stateService;
      }



        @GetMapping("/users/viewstate")
        public String viewState(Model view) {
            System.out.println("hello");
          List<State> states = stateService.findAll();

            view.addAttribute("state", states);

            return "/users/viewstate"; }


        @GetMapping("/viewstate/{id}")
        public String showDetails(@PathVariable long id, Model view){
            System.out.println("entro");
            State state = stateService.findByName("state");
            view.addAttribute("state", state);

            return "redirect:/viewstate";
        }
}