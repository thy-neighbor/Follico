package com.neighbor.hair.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "product")
public class Product extends EntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @NotEmpty
  private String name;

  @NotEmpty
  private String sku;

  //private List<String> ingredients = new ArrayList<>();

  public Product() {
  }

  public Product(String name, String sku) {
    this.name = name;
    this.sku = sku;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getSku() {
    return sku;
  }

  public void setId(Long id) { this.id = id; }

  public void setName(String name) {
    this.name = name;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }
}