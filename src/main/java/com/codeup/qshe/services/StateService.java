package com.codeup.qshe.services;


import com.codeup.qshe.models.State;
import com.codeup.qshe.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {
    private Staterepository staterepository;
    private StatePopulations populations;
    private StateEducations educations;
    private StatePoverties poverties;
    private Crimes crimes;
    private States states;

    @Autowired
    public StateService(Staterepository staterepository, StatePopulations populations,
                        StateEducations educations, StatePoverties poverties, Crimes crimes,
                        States states) {
            this.staterepository = staterepository;
            this.populations = populations;
            this.educations = educations;
            this.poverties = poverties;
            this.crimes = crimes;
            this.states = states;
    }


    public States getStates() { return states; }

    public Staterepository getStaterepository() {
        return staterepository;
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

    public Crimes getCrimes() {
        return crimes;
    }


    public List<State> findAll(){
        Iterable<State> states = staterepository.findAll();
        return (List<State>) states;
    }

    public State findByName (String stateName) {
        State state = staterepository.findByName(stateName);
        return state;
    }


}
