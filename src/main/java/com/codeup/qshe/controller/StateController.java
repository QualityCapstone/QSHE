package com.codeup.qshe.controller;

import com.codeup.qshe.models.State;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.services.FlickrService;
import com.codeup.qshe.services.PostTopicService;
import com.codeup.qshe.services.StateMetricService;
import com.codeup.qshe.services.StateService;
import com.codeup.qshe.services.user.UserService;
import com.flickr4java.flickr.FlickrException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
 class StateController {
  private final StateService stateDao;
  private final StateMetricService metricDao;
  private final PostTopicService postDao;
  private final UserService userDao;

    @Value("${flickr-key}")
    private String apiKey;
    @Value("${flickr-secret}")
    private String sharedSecret;

    @Autowired
  public StateController(StateService stateDao,
                         StateMetricService metricDao,
                         UserService userDao,
                         PostTopicService postDao){

      this.stateDao = stateDao;
      this.metricDao = metricDao;
      this.userDao = userDao;
      this.postDao = postDao;
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

        model.addAttribute("topPosts", postDao.getPosts().findTop3ByState(state));


        model.addAttribute("calcR",metricDao.calculatedRatingsByState(state).getMetricValues());
        model.addAttribute("userR", metricDao.averageUserRatingsByState(state).getMetricValues());
        model.addAttribute("ranking", metricDao.getMetricRankings(state));

        model.addAttribute("special", metricDao.generateUniqueMetrics(state));

        model.addAttribute("states", stateDao.getStates().findAll());
        model.addAttribute("state", state);
        model.addAttribute("photos", f.getPhotos(state.getName(),1));
        return "states/viewstate";
  }



    @GetMapping("/state/mine")
    public String myState() throws FlickrException {
        User user = userDao.getLoggedInUser();
        State state = stateDao.getStates().findByName(user.getProfile().getUserState());

        return "redirect:/state/" + state.getAbbr();
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

            model.addAttribute("calcA",metricDao.calculatedRatingsByState(stateA).getMetricValues());
            model.addAttribute("userA", metricDao.averageUserRatingsByState(stateA).getMetricValues());
            model.addAttribute("rankingA", metricDao.getMetricRankings(stateA));
            model.addAttribute("specialA", metricDao.generateUniqueMetrics(stateA));

            model.addAttribute("calcB",metricDao.calculatedRatingsByState(stateB).getMetricValues());
            model.addAttribute("userB", metricDao.averageUserRatingsByState(stateB).getMetricValues());
            model.addAttribute("rankingB", metricDao.getMetricRankings(stateB));
            model.addAttribute("specialB", metricDao.generateUniqueMetrics(stateB));

            return "states/compare";


    }



}