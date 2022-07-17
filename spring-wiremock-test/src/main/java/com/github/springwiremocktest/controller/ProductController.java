package com.github.springwiremocktest.controller;

import com.github.springwiremocktest.controller.response.ProductResponseEntity;
import com.github.springwiremocktest.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
public class ProductController {
  private ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("{id}")
  public ProductResponseEntity getProduct(@PathVariable("id") String id) {
    return productService.findById(id);
  }
}
