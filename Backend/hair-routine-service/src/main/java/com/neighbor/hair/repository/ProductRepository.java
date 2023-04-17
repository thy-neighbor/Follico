package com.neighbor.hair.repository;

import com.neighbor.hair.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
  Long deleteByIdCount(Long id);
}
