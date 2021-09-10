package com.java17;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties("test")
// @ConstructorBinding
public record TestProperties(String name, int age) {
}
