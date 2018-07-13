package com.codeup.qshe.controller;

import com.codeup.qshe.models.State;
import com.codeup.qshe.models.user.StateMetric;
import com.codeup.qshe.services.FlickrService;
import com.codeup.qshe.services.StateMetricService;
import com.codeup.qshe.services.StateService;
import com.flickr4java.flickr.FlickrException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
 class StateController {
  private final StateService stateDao;
  private final StateMetricService metricDao;

    @Value("${flickr-key}")
    private String apiKey;
    @Value("${flickr-secret}")
    private String sharedSecret;

    @Autowired
  public StateController(StateService stateDao, StateMetricService metricDao){
      this.stateDao = stateDao;
      this.metricDao = metricDao;
  }

  @GetMapping("/us")
  public String viewAll(Model model) {

      model.addAttribute("states", stateDao.getStates().findAll());
      model.addAttribute("ratings", metricDao.averageUserRatingsByState());

      return "states/map";
  }

    @GetMapping("/state/{abbr}")
    public String viewState(@PathVariable String abbr, Model model) throws FlickrException {
      State state = stateDao.getStates().findByAbbr(abbr);

      FlickrService f = new FlickrService(apiKey, sharedSecret);

        model.addAttribute("states", stateDao.getStates().findAll());
        model.addAttribute("state", state);
        model.addAttribute("photos", f.getPhotos(state.getName(),1));
        return "states/viewstate";
  }

        @GetMapping("/state/compare/{abbr}/{abbr2}")
    public String compareState(@PathVariable String abbr, @PathVariable String abbr2, Model model) throws FlickrException {

            State stateA = stateDao.getStates().findByAbbr(abbr);
            State stateB = stateDao.getStates().findByAbbr(abbr2);

            FlickrService f = new FlickrService(apiKey, sharedSecret);

            model.addAttribute("states", stateDao.getStates().findAll());

            model.addAttribute("stateA", stateA);
            model.addAttribute("stateB", stateB);

            model.addAttribute("photoA", f.getPhotos(stateA.getName(),1));
            model.addAttribute("photoB", f.getPhotos(stateB.getName(),1));

      return "states/compare";


    }



}