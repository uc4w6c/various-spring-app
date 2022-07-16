package com.github.springwiremocktest.service;

import com.github.springwiremocktest.controller.request.OrderPurchaseRequest;
import com.github.springwiremocktest.dao.api.StockDao;
import com.github.springwiremocktest.entity.StockReduceEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderService {
  private StockDao stockDao;

  public OrderService(StockDao stockDao) {
    this.stockDao = stockDao;
  }

  public void purchase(OrderPurchaseRequest orderPurchaseRequest) {
    StockReduceEntity stockReduceEntity = new StockReduceEntity(orderPurchaseRequest.productId(), orderPurchaseRequest.quantity());
    stockDao.reduce(stockReduceEntity);
  }
}
