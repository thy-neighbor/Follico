package com.neighbor.hair.service;

import com.neighbor.hair.entity.Product;
import com.neighbor.hair.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//this will probably become a microservice

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public List<Product> findAll() {
    return productRepository.findAll();
  }

  @Override
  public Optional<Product> findById(Long id) {
    return productRepository.findById(id);
  }

  @Override
  public Optional<Product> save(Product product) {
    return Optional.of(productRepository.save(product));
  }

  @Override
  public Optional<Product> update(Long id, Product product)
      throws NotFoundException, IllegalArgumentException {
    if (!id.equals(product.getId())) {
      throw new IllegalArgumentException(
          "Id provided doesn't match the object id: {" + id + "} vs {" + product.getId() + "}");
    }

    Optional<Product> persistedProduct = productRepository.findById(id);

    if (!persistedProduct.isPresent()) {
      throw new NotFoundException("Entity with id {" + id + "} was not found");
    }

    return Optional.of(productRepository.save(product));
  }

  @Override
  public void delete(Long id) throws NotFoundException {
    if (!(productRepository.deleteByIdCount(id) > 0)) {
      throw new NotFoundException("Entity with id {" + id + "} was not found");
    }
  }

  @PostConstruct
  private void defaultSetup() {
    if (productRepository.count() == 0) {

      Product productOne = new Product("Shea Butter", "856066000027");
      Product productTwo = new Product("Argan Oil", "856066000029");

      productRepository.save(productOne);
      productRepository.save(productTwo);
    }
  }
}
