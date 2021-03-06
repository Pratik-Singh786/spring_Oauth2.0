package com.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class Config extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
//        http
//                .authorizeRequests(a -> a
//                        .antMatchers("/", "/error", "/webjars/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .exceptionHandling(e -> e
//                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
//                )
//                .csrf(c -> c
//                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                )
//                .logout(l -> l
//                        .logoutSuccessUrl("/").permitAll()
//                ).oauth2Login();
//
        http.authorizeRequests()
                .antMatchers("/", "/error", "/webjars/**").permitAll()   //for these apis i don't need to be authenticated
                .anyRequest().authenticated() //and for any other apis i need authentication
                .and()
                .exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.INTERNAL_SERVER_ERROR))
                .and()
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())//csrf token(it is inside our code) helps us to keep safe from malicious web attacks
                .and()
                .logout()
                .logoutSuccessUrl("/").permitAll()
                .and()
                .oauth2Login();//oauth2Login means authentication is not my responsibility
    }
}
