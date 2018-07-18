package com.codeup.qshe.models.state;

import com.codeup.qshe.models.State;
import com.codeup.qshe.models.user.StateMetric;
import com.codeup.qshe.models.user.StateUserRating;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class StateAverageRanking {

    private Long stateId;
    private String name;
    private String abbr;

    private HashMap<StateMetric, Float> metrics = new HashMap<>();

    public StateAverageRanking(Long stateId, String name, String abbr, HashMap<StateMetric, Float> metrics) {
        this.stateId = stateId;
        this.name = name;
        this.abbr = abbr;
        this.metrics = metrics;
    }

    public StateAverageRanking(Long stateId, String name, String abbr) {
        this.stateId = stateId;
        this.name = name;
        this.abbr = abbr;
    }


    public StateAverageRanking(State state) {
        this.stateId = state.getId();
        this.name = state.getName();
        this.abbr = state.getAbbr();
    }

    public Long getstateId() {
        return stateId;
    }

    public void setstateId(Long stateId) {
        this.stateId = stateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addMetric(StateMetric metric, float value) {
        this.metrics.put(metric, value);
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public HashMap<StateMetric, Float> getMetrics() {
        return metrics;
    }

    public void setMetrics(HashMap<StateMetric, Float> metrics) {
        this.metrics = metrics;
    }


    public HashMap<String, Float> getMetricValues() {

        HashMap<String, Float> easyMap = new HashMap<>();

        for (HashMap.Entry<StateMetric, Float> entry : metrics.entrySet()) {
            StateMetric key = entry.getKey();
            float value = entry.getValue();
            easyMap.put(key.getName(), value);
        }

        return easyMap;
    }

}
