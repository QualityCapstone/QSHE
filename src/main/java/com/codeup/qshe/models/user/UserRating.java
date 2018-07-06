package com.codeup.qshe.models.user;

import javax.persistence.*;

@Entity
@Table (name ="state_user_rating")
public class UserRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
    @Column (name="user_id", nullable = false)
    private Long userId;

//    @ManyToOne
    @Column (name="state_id", nullable = false)
    private Long stateId;

    @Column (name="user_rating", nullable = false)
    private float userRate;


    public UserRating (){}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public float getUserRate() {
        return userRate;
    }

    public void setUserRate(float userRate) {
        this.userRate = userRate;
    }

    public UserRating(Long id, Long stateId, Long userId, float userRate){
        this.id = id;
        this.stateId = stateId;
        this.userId = userId;
        this.userRate = userRate;
    }

    public UserRating(Long userId, Long stateId, float userRate) {
        this.userId = userId;
        this.stateId = stateId;
        this.userRate = userRate;
    }



}
