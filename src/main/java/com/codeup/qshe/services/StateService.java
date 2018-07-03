package com.codeup.qshe.services;

import com.codeup.qshe.models.StateCrime;
import com.codeup.qshe.repositories.CrimeRepository;
import com.codeup.qshe.repositories.StatePopulations;
import com.codeup.qshe.repositories.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StateService {
    private States states;
    private StatePopulations populations;
    private CrimeRepository crimes;

    @Autowired
    public StateService(States states, StatePopulations populations, CrimeRepository crimes) {
        this.states = states;
        this.populations = populations;
        this.crimes = crimes;
    }

    public States getStates() {
        return states;
    }

    public StatePopulations getPopulations() {
        return populations;
    }

    public CrimeRepository getCrimes() {
        return crimes;
    }

}
