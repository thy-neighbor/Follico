package com.neighbor.hair.controller;

import com.neighbor.hair.entity.Product;
import com.neighbor.hair.service.ProductService;
import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/product")
public class ProductController {

  @Autowired
  ProductService productService;

  @GetMapping("/{id}")
  public ResponseEntity<Optional<Product>> read(@PathVariable Long id) {

    Optional<Product> data = productService.findById(id);

    return data.isPresent() ? new ResponseEntity<>(data, HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @GetMapping()
  public ResponseEntity<List<Product>> readAll() {

    List<Product> data = productService.findAll();

    return data.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
        : new ResponseEntity<>(data, HttpStatus.OK);
  }

  @PostMapping()
  public ResponseEntity<?> create(@Valid @RequestBody Product product,
      final BindingResult bindingResult) {

    if (!bindingResult.hasErrors()) {
      Optional<Product> data = productService.save(product);

      if (data.isPresent()) {
        return new ResponseEntity<>(data.get(), HttpStatus.CREATED);
      }
    }

    return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
  }

  @PutMapping()
  public ResponseEntity<?> edit(
      @PathVariable Long id, @Valid @RequestBody Product product,
      final BindingResult bindingResult) {

    if (!bindingResult.hasErrors()) {

      Optional<Product> data = Optional.empty();

      try {
        data = productService.update(id, product);
      } catch (Exception e) {
        e.printStackTrace();
      }

      if (data.isPresent()) {
        return new ResponseEntity<>(data.get(), HttpStatus.CREATED);
      }
    }
    return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping()
  public ResponseEntity<?> delete(@PathVariable Long id) {

    try {
      productService.delete(id);
    } catch (NotFoundException e) {
      e.printStackTrace();
    }

    return ResponseEntity.noContent().build();
  }
}
