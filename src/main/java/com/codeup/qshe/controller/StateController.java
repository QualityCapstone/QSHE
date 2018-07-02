package com.codeup.qshe.controller;


import com.codeup.qshe.models.user.State;
import com.codeup.qshe.repositories.States;
import com.codeup.qshe.services.user.StateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
 class StateController {
  private final StateService stateService;


  public StateController(StateService stateService){
      this.stateService = stateService;
  }



    @GetMapping("/viewstate")
    public String viewState(Model model) {


        model.addAttribute("model", model);

        return "users/viewstate"; }
}
