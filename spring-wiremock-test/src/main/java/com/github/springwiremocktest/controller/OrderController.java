package com.github.springwiremocktest.controller;

import com.github.springwiremocktest.controller.request.OrderPurchaseRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void purchase(@RequestBody OrderPurchaseRequest orderPurchaseRequest) {
    System.out.println(orderPurchaseRequest);
  }
}
