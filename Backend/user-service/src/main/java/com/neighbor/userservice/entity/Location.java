package com.neighbor.userservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class Location {
    @Id
    private Integer locationId;

    private String city;
    private int zipCode;
    private String state;

    @OneToOne
    @MapsId
    private Hair hair;

    public Location(){

    }

    public Location(int zipCode, Hair hair) {
        this.zipCode = zipCode;
        this.hair = hair;
    }

    public Location(String city, int zipCode, String state, Hair hair) {

        this.city = city;
        this.zipCode = zipCode;
        this.state = state;
        this.hair = hair;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Hair getHair() {
        return hair;
    }

    public void setHair(Hair hair) {
        this.hair = hair;
    }
}
