package com.codeup.qshe.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class SiteSetting {

    @Id
    private Integer id = 1;
    // Record if data is populated on the site,
    // false value will trigger a full reload
    @Column
    private Boolean isPopulated = false;

    public SiteSetting() {}

    public SiteSetting(Boolean isPopulated) {
        this.isPopulated = isPopulated;
    }

    public Boolean getPopulated() {
        return isPopulated;
    }

    public void setPopulated(Boolean populated) {
        isPopulated = populated;
    }
}
