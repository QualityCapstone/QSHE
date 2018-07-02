package com.codeup.qshe.services.user;

import com.codeup.qshe.models.user.State;
import com.codeup.qshe.repositories.States;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {
    private final States states;

    public StateService(States states){
        this.states = states;
    }


    List<State> findAll(){
        return states.findAll();

    }

    public State findOne(String name) {
        State state = (State) states.findByName(name);
        return state;
    }
}
