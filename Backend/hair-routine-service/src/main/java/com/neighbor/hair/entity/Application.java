package com.neighbor.hair.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/*
* Application specifies the product being used(product), what action is
* taken with this product(action), where is it used(location), and how often is this
* application used(frequency)
*
* Im not in love with the name
* */
@Entity
public class Application extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id" , referencedColumnName = "id")
    private Product product;    //what product is being used

    @NotNull
    @OneToOne(mappedBy = "application", cascade = CascadeType.ALL)
    @JoinColumn(name = "action_id", referencedColumnName = "id")
    private Action action;  //what is being done with product (one to one)

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="stepId" , nullable = false)
    private Step step;    //Step it belongs too

    public Application() {
    }

    public Application(Product product, Action action) {
        this.product = product;
        this.action = action;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }
}
