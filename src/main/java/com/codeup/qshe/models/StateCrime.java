package com.codeup.qshe.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class StateCrime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private State state;

    @Column
    private String stateAbbr;

    @Column
    private Long population;

    @Column
    private LocalDate dateDataCreated;

    @Column
    private Long year;

    @Column
    private Long violentCrimeCount;

    @Column
    private Long homicideCount;

    @Column
    private Long rapeCount;

    @Column
    private Long robberyCount;

    @Column
    private Long assaultCount;

    @Column
    private Long propertyCrimeCount;

    @Column
    private Long burglaryCount;

    @Column
    private Long larcenyCount;

    @Column
    private Long motorTheftCount;

    @Column
    private Long arsonCount;

    public StateCrime() {}

    public StateCrime(State state, Long population, LocalDate dateDataCreated) {
        this.state = state;
        this.population = population;
        this.dateDataCreated = dateDataCreated;
    }

    public StateCrime(
            State state,
            String stateAbbr,
            Long population,
            Long year,
            Long violentCrimeCount,
            Long homicideCount,
            Long rapeCount,
            Long robberyCount,
            Long assaultCount,
            Long propertyCrimeCount,
            Long burglaryCount,
            Long larcenyCount,
            Long motorTheftCount,
            Long arsonCount) {
        this.state = state;
        this.stateAbbr = stateAbbr;
        this.population = population;
        this.year = year;
        this.violentCrimeCount = violentCrimeCount;
        this.homicideCount = homicideCount;
        this.rapeCount = rapeCount;
        this.robberyCount = robberyCount;
        this.assaultCount = assaultCount;
        this.propertyCrimeCount = propertyCrimeCount;
        this.burglaryCount = burglaryCount;
        this.larcenyCount = larcenyCount;
        this.motorTheftCount = motorTheftCount;
        this.arsonCount = arsonCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }


    public LocalDate getDateDataCreated() {
        return dateDataCreated;
    }

    public void setDateDataCreated(LocalDate dateDataCreated) {
        this.dateDataCreated = dateDataCreated;
    }


    @Override
    public String toString() {
        return "StatePopulation{" +
                "id=" + id +
                ", state=" + state +
                ", population=" + population +
                ", dateDataCreated=" + dateDataCreated +
                '}';
    }

    public String getStateAbbr() {
        return stateAbbr;
    }

    public void setStateAbbr(String stateAbbr) {
        this.stateAbbr = stateAbbr;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public Long getViolentCrimeCount() {
        return violentCrimeCount;
    }

    public void setViolentCrimeCount(Long violentCrimeCount) {
        this.violentCrimeCount = violentCrimeCount;
    }

    public Long getHomicideCount() {
        return homicideCount;
    }

    public void setHomicideCount(Long homicideCount) {
        this.homicideCount = homicideCount;
    }

    public Long getRapeCount() {
        return rapeCount;
    }

    public void setRapeCount(Long rapeCount) {
        this.rapeCount = rapeCount;
    }

    public Long getRobberyCount() {
        return robberyCount;
    }

    public void setRobberyCount(Long robberyCount) {
        this.robberyCount = robberyCount;
    }

    public Long getAssaultCount() {
        return assaultCount;
    }

    public void setAssaultCount(Long assaultCount) {
        this.assaultCount = assaultCount;
    }

    public Long getPropertyCrimeCount() {
        return propertyCrimeCount;
    }

    public void setPropertyCrimeCount(Long propertyCrimeCount) {
        this.propertyCrimeCount = propertyCrimeCount;
    }

    public Long getBurglaryCount() {
        return burglaryCount;
    }

    public void setBurglaryCount(Long burglaryCount) {
        this.burglaryCount = burglaryCount;
    }

    public Long getLarcenyCount() {
        return larcenyCount;
    }

    public void setLarcenyCount(Long larcenyCount) {
        this.larcenyCount = larcenyCount;
    }

    public Long getMotorTheftCount() {
        return motorTheftCount;
    }

    public void setMotorTheftCount(Long motorTheftCount) {
        this.motorTheftCount = motorTheftCount;
    }

    public Long getArsonCount() {
        return arsonCount;
    }

    public void setArsonCount(Long arsonCount) {
        this.arsonCount = arsonCount;
    }


    public Long getViolentCrimeTotal() {
        return this.getHomicideCount() +
                this.getRapeCount() + this.getRobberyCount() +
                this.getAssaultCount();
    }

    public Long getNonViolentCrimeTotal() {
        return this.propertyCrimeCount + this.burglaryCount +
                this.larcenyCount + this.motorTheftCount + this.arsonCount;

    }

    public Long  getTotalCrime() {
        return getViolentCrimeTotal() + getNonViolentCrimeTotal();
    }

}