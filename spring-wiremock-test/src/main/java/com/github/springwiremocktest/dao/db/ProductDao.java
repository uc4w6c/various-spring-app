package com.github.springwiremocktest.dao.db;

import com.github.springwiremocktest.entity.ProductEntity;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class ProductDao {
  public Optional<ProductEntity> findById(String id) {
    return Optional.of(new ProductEntity(id, 1000));
  }
}
