package com.codeup.qshe.services;

import com.codeup.qshe.models.StateEducation;
import com.codeup.qshe.repositories.StateEducations;
import com.codeup.qshe.repositories.StatePopulations;
import com.codeup.qshe.repositories.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StateService {
    private States states;
    private StatePopulations populations;
    private StateEducations educations;

    @Autowired
    public StateService(States states, StatePopulations populations, StateEducations educations) {
        this.states = states;
        this.populations = populations;
        this.educations = educations;
    }

    public States getStates() {
        return states;
    }

    public StatePopulations getPopulations() {
        return populations;
    }

    public StateEducations getEducations() {
        return educations;
    }
}
