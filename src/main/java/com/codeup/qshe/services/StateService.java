package com.codeup.qshe.services;

import com.codeup.qshe.models.StateEducation;
import com.codeup.qshe.repositories.StateEducations;
import com.codeup.qshe.repositories.StatePopulations;
import com.codeup.qshe.repositories.StatePoverties;
import com.codeup.qshe.repositories.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StateService {
    private States states;
    private StatePopulations populations;
    private StateEducations educations;
    private StatePoverties poverties;

    @Autowired
    public StateService(States states, StatePopulations populations,
                        StateEducations educations, StatePoverties poverties) {
        this.states = states;
        this.populations = populations;
        this.educations = educations;
        this.poverties = poverties;
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


    public StatePoverties getPoverties() {
        return poverties;
    }
}
