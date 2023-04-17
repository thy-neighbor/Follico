package com.neighbor.hair.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neighbor.hair.constraints.annotations.Legend;
import com.neighbor.hair.constraints.legends.LegendType;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Step extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Legend(LegendType.STEPNAME)
    private String stepName;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "step")
    private List<Application> applications = new LinkedList<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="hairRoutineId" , nullable = false)
    private HairRoutine hairRoutine;

    public Step() {
    }

    public Step(String stepName) {
        this.stepName = stepName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(
        List<Application> applications) {
        this.applications = applications;
    }

    public HairRoutine getHairRoutine() {
        return hairRoutine;
    }

    public void setHairRoutine(HairRoutine hairRoutine) {
        this.hairRoutine = hairRoutine;
    }
}
