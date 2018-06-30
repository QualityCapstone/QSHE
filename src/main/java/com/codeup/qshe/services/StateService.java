package com.codeup.qshe.services;

import com.codeup.qshe.repositories.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StateService {
    private States states;


    @Autowired
    public StateService(States states) {
        this.states = states;
    }

    public States getStates() {
        return states;
    }



}
