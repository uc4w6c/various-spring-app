package com.example.springauthorizationserverpoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    /*
    http
         .authorizeHttpRequests(authorize ->
             authorize.anyRequest().authenticated())
        .authorizeHttpRequests(authorize ->
            authorize.requestMatchers("/**").permitAll())
        .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);*/

    /*
    // 1.0.1の書き方
    http.authorizeHttpRequests()
        .anyRequest().authenticated()
        .and()
        .oauth2Login();
    http.authorizeHttpRequests();
     */

    http
        .authorizeHttpRequests((authorize) -> authorize
            .anyRequest().authenticated()
        )
        // Form login handles the redirect to the login page from the
        // authorization server filter chain
        .formLogin(Customizer.withDefaults());

    // http.addFilterAfter(new LoginFilter(jwtDecoder), UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
