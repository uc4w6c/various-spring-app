package com.github.springwiremocktest.controller.response;

/**
 * 商品レスポンス
 * @param id 商品ID
 * @param price 価格
 * @param quantity 在庫数
 */
public record ProductResponseEntity(String id, int price, int quantity) {}
