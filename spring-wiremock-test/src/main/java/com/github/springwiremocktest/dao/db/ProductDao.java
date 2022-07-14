package com.github.springwiremocktest.dao.db;

import com.github.springwiremocktest.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductDao {
  public ProductEntity findById(String id) {
    return new ProductEntity(id, 1000);
  }
}
