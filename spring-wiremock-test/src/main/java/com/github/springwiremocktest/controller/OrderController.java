package com.github.springwiremocktest.controller;

import com.github.springwiremocktest.controller.request.OrderPurchaseRequest;
import com.github.springwiremocktest.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {
  private OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void purchase(@RequestBody OrderPurchaseRequest orderPurchaseRequest) {
    orderService.purchase(orderPurchaseRequest);
  }
}
