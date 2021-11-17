package com.neighbor.hairroutineservice.entity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/*
* HairRoutine - high level object containing the list of hair
* care routine steps
* */
@Entity
public class HairRoutine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hairRoutineId;

    private int frequency;  //how often: "every {frequency} week(s)"

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "hairRoutine")
    private List<Step> steps = new LinkedList<>();    //list of steps



}
