package com.java17.configurationtest;

import java.net.InetAddress;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("my.service")
public record MyProperties(boolean enabled, InetAddress remoteAddress) {
}
