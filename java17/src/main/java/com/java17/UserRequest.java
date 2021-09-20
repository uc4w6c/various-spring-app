package com.java17;

import javax.validation.constraints.NotBlank;

public record UserRequest(@NotBlank String name, int age) {}
