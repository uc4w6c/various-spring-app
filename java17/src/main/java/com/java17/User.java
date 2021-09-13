package com.java17;

import javax.validation.constraints.NotBlank;

import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

public record User(@NotBlank String name, int age) {
}
