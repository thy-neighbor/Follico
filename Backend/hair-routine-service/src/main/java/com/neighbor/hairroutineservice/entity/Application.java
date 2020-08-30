package com.neighbor.hairroutineservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/*
* Application specifies the product being used(product), what action is
* taken with this product(action), where is it used(location), and how often is this
* application used(frequency)
* */
@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //private Product product;    //idk what this looks like yet

    @OneToOne(mappedBy = "application", cascade = CascadeType.ALL)
    private Action action;  //what is being done with product (one to one)

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="stepId" , nullable = false)
    private Step step;    //Step it belongs too
}
