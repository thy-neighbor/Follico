package com.neighbor.hair.service;

import com.neighbor.hair.entity.Product;
import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;


public interface ProductService {

  List<Product> findAll();

  Optional<Product> findById(Long id);

  Optional<Product> save(Product product);

  Optional<Product> update(Long id, Product product)
      throws NotFoundException, IllegalArgumentException;

  void delete(Long id) throws NotFoundException;
}