package com.codeup.qshe.services;


import com.codeup.qshe.models.State;
import com.codeup.qshe.repositories.StateEducations;
import com.codeup.qshe.repositories.CrimeRepository;
import com.codeup.qshe.repositories.StatePopulations;
import com.codeup.qshe.repositories.StatePoverties;
import com.codeup.qshe.repositories.Staterepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {
    private Staterepository staterepository;
    private StatePopulations populations;
    private StateEducations educations;
    private StatePoverties poverties;
    private CrimeRepository crimes;

    @Autowired
    public StateService(Staterepository staterepository, StatePopulations populations,
                        StateEducations educations, StatePoverties poverties, CrimeRepository crimes) {
            this.staterepository = staterepository;
            this.populations = populations;
            this.educations = educations;
            this.poverties = poverties;
            this.crimes = crimes;
    }



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

    public CrimeRepository getCrimes() {
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
