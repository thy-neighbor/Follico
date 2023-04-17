package com.neighbor.hair.entity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

/*
* HairRoutine - high level object containing the list of hair
* care routine steps
* */
@Entity
public class HairRoutine extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Range(min = 0, max = 730)  //put 2 years max just incase
    private Integer frequency;  //how often: "every {frequency} day(s)"

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "hairRoutine")
    @NotNull
    private List<Step> steps = new LinkedList<>();    //list of steps

    public HairRoutine(Integer frequency) {
        this.frequency = frequency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }
}
