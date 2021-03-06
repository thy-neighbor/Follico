package com.neighbor.userservice.entity;

import javax.persistence.*;

@Entity
public class Hair {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String hairType;
    private String porosity;
    private String damage;

    @OneToOne
    @MapsId
    private User user;  //uses the same id as User since they are related

    @OneToOne(mappedBy = "hair", cascade = CascadeType.ALL)
    private Location location;


    public Hair() {
    }

    public Hair(String hairType, Location location) {
        this.hairType = hairType;
        this.location = location;
    }

    public Hair(String hairType, int zipcode) {
        this.hairType = hairType;
        this.location = new Location(zipcode,this);
    }

    public Hair(String hairType, int zipcode, User user) {
        this.hairType = hairType;
        this.location = new Location(zipcode,this);
        this.user = user;
    }

    public Hair(String hairType, String porosity, String damage, int zipcode) {
        this.hairType = hairType;
        this.porosity = porosity;
        this.damage = damage;
        this.location = new Location(zipcode,this);
    }

    public Hair(String hairType, String porosity, String damage, Location location) {
        this.hairType = hairType;
        this.porosity = porosity;
        this.damage = damage;
        this.location = location;
    }

    public Hair(String hairType, String porosity, String damage, User user, Location location) {
        this.hairType = hairType;
        this.porosity = porosity;
        this.damage = damage;
        this.user = user;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHairType() {
        return hairType;
    }

    public void setHairType(String hairType) {
        this.hairType = hairType;
    }

    public String getPorosity() {
        return porosity;
    }

    public void setPorosity(String porosity) {
        this.porosity = porosity;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
