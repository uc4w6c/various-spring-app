package com.java17.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record UserResponse(String name, boolean hasBook, @JsonIgnore boolean isStudent) {}
