package com.wolverineteam.ngpuppies.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //Configuration for testing purposes
        http.authorizeRequests().anyRequest().permitAll();

        //http.authorizeRequests()
        //        .antMatchers("/").permitAll()
        //        .antMatchers("/client/**").hasRole("CLIENT")
        //        .antMatchers("/admin/**").hasRole("ADMIN")
        //        .anyRequest().authenticated()
        //        .and()
        //        .formLogin()
        //            .loginPage("/login")
        //            .loginProcessingUrl("/authenticate")
        //            .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(User.withUsername("admin").password("{noop}admin").roles("CLIENT", "ADMIN"))
                .withUser(User.withUsername("client").password("{noop}client").roles("CLIENT"));
    }
}
