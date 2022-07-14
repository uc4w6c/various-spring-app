package com.github.springwiremocktest.controller;

import com.github.springwiremocktest.controller.response.ProductResponseEntity;
import com.github.springwiremocktest.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("products")
public class ProductController {
  private ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("{id}")
  public ProductResponseEntity getProduct(@PathParam("id") String id) {
    return productService.findById(id);
  }
}
