package com.codeup.qshe.models;


import javax.persistence.*;

@Entity
@Table
public class StatePoverty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer year;

    @ManyToOne
    private State state;

    @Column
    private String origStateId;

    @Column
    private Double femalePoverty;

    @Column
    private Double malePoverty;

    public StatePoverty() {};

    public StatePoverty(Integer year, State state, String origStateId, Double femalePoverty, Double malePoverty) {
        this.year = year;
        this.state = state;
        this.origStateId = origStateId;
        this.femalePoverty = femalePoverty;
        this.malePoverty = malePoverty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getOrigStateId() {
        return origStateId;
    }

    public void setOrigStateId(String origStateId) {
        this.origStateId = origStateId;
    }

    public Double getFemalePoverty() {
        return femalePoverty;
    }

    public void setFemalePoverty(Double femalePoverty) {
        this.femalePoverty = femalePoverty;
    }

    public Double getMalePoverty() {
        return malePoverty;
    }

    public void setMalePoverty(Double malePoverty) {
        this.malePoverty = malePoverty;
    }
}
