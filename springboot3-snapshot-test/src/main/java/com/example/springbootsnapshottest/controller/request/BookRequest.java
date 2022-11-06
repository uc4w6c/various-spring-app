package com.example.springbootsnapshottest.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record BookRequest(
    @Schema(title = "id")
    String id,
    @Schema(title = "名前")
    String name,
    @Schema(title = "値段")
    int price,
    @Schema(title = "種類")
    BookKind kind
) {}
