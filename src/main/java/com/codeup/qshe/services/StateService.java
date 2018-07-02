package com.codeup.qshe.services;

import com.codeup.qshe.repositories.StatePopulations;
import com.codeup.qshe.repositories.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StateService {
    private States states;
    private StatePopulations populations;

    @Autowired
    public StateService(States states, StatePopulations populations) {
        this.states = states;
        this.populations = populations;
    }

    public States getStates() {
        return states;
    }

    public StatePopulations getPopulations() {
        return populations;
    }
}
