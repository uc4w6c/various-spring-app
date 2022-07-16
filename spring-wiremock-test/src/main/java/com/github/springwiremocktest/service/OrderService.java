package com.github.springwiremocktest.service;

import com.github.springwiremocktest.controller.request.OrderPurchaseRequest;
import com.github.springwiremocktest.dao.api.StockDao;
import com.github.springwiremocktest.dao.db.ProductDao;
import com.github.springwiremocktest.entity.StockReduceEntity;
import com.github.springwiremocktest.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class OrderService {
  private ProductDao productDao;
  private StockDao stockDao;

  public OrderService(ProductDao productDao, StockDao stockDao) {
    this.productDao = productDao;
    this.stockDao = stockDao;
  }

  public void purchase(OrderPurchaseRequest orderPurchaseRequest) {
    productDao
        .findById(orderPurchaseRequest.productId())
        .orElseThrow(ProductNotFoundException::new);

    StockReduceEntity stockReduceEntity =
        new StockReduceEntity(orderPurchaseRequest.productId(), orderPurchaseRequest.quantity());
    stockDao.reduce(stockReduceEntity);
  }
}
