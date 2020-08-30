package com.neighbor.hairroutineservice.entity;

import javax.persistence.*;

@Entity
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //may take this out
    private Integer id;

    private String description;  //what is being done with product

    private String location; //where is it applied

    private int frequency;  //how many times in that moment: "{frequency} times"

    @OneToOne
    @MapsId
    private Application application;    //one to one, revisit id generation
}
