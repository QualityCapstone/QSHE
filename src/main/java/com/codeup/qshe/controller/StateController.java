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
        return "states/viewstate";
  }

        @GetMapping("/state/compare/{abbr}/{abbr2}")
    public String compareState(@PathVariable String abbr, @PathVariable String abbr2, Model model) {

            State stateA = stateDao.getStates().findByAbbr(abbr);
            State stateB = stateDao.getStates().findByAbbr(abbr2);

            model.addAttribute("states", stateDao.getStates().findAll());

            model.addAttribute("stateA", stateA);
            model.addAttribute("stateB", stateB);

      return "states/compare";


    }



}