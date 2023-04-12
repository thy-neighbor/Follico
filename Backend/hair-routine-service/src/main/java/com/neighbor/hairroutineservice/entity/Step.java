package com.neighbor.hairroutineservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neighbor.hairroutineservice.constraints.annotations.Legend;
import com.neighbor.hairroutineservice.constraints.legends.LegendType;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stepId;

    @Legend(LegendType.STEPNAME)
    private String stepName;  //might use a enum to validate?

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "step")
    private List<Application> applications = new LinkedList<>();    //just need a data structure with order

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="hairRoutineId" , nullable = false)
    private HairRoutine hairRoutine;
}
