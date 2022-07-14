package com.github.springwiremocktest.service;

import com.github.springwiremocktest.controller.response.ProductResponseEntity;
import com.github.springwiremocktest.dao.api.StockDao;
import com.github.springwiremocktest.dao.db.ProductDao;
import com.github.springwiremocktest.entity.HelloEntity;
import com.github.springwiremocktest.entity.ProductEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class ProductService {
  private StockDao stockDao;
  private ProductDao productDao;

  public ProductService(StockDao stockDao, ProductDao productDao) {
    this.stockDao = stockDao;
    this.productDao = productDao;
  }

  public ProductResponseEntity findById(String id) {
    // 本来的にはここをOptionalにして存在しない場合はProductNotFoundExceptionを投げる
    ProductEntity productEntity = productDao.findById(id);
    int quantity = stockDao.findById(id).quantity();

    return new ProductResponseEntity(id, productEntity.price(), quantity);
  }
}
