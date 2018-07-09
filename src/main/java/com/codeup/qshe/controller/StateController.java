package com.codeup.qshe.controller;

import com.codeup.qshe.models.State;
import com.codeup.qshe.services.StateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
 class StateController {
  private final StateService stateDao;

  public StateController(StateService stateDao){
      this.stateDao = stateDao;
  }

  @GetMapping("/us")
  public String viewAll(Model model) {

      model.addAttribute("states", stateDao.getStates().findAll());
      return "states/map";
  }

    @GetMapping("/state/{abbr}")
    public String viewState(@PathVariable String abbr, Model model) {
      State state = stateDao.getStates().findByAbbr(abbr);
        model.addAttribute("states", stateDao.getStates().findAll());
        model.addAttribute("state", state);
        return "states/viewstate"; }


        @GetMapping("/users/viewstate")
        public String viewState(Model view) {
            System.out.println("hello");
          List<State> states = stateDao.findAll();
            view.addAttribute("state", states);

            return "/users/viewstate";
      }


        @GetMapping("/viewstate/{id}")
        public String showDetails(@PathVariable long id, Model view){
            System.out.println("entro");
            State state = stateDao.findByName("state");
            view.addAttribute("state", state);

            return "redirect:/viewstate";
        }
}