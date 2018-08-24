package com.wolverineteam.ngpuppies.config;

import com.wolverineteam.ngpuppies.services.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //Configuration for testing purposes
       //http.authorizeRequests().anyRequest().permitAll()

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

//
        http
            .cors()
            .and()
            .csrf()
            .disable()
            .authorizeRequests()
            .antMatchers("/login").permitAll()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/client/**").hasRole("USER")
            .anyRequest().authenticated()
            .and()
            .addFilter(new JwtAuthenticationFilter(authenticationManager()))
            .addFilter(new JwtAuthorizationFilter(authenticationManager(), this.userService))
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
}

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(this.userService)
                    .passwordEncoder(this.bCryptPasswordEncoder);
        }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser(User.withUsername("admin").password("{noop}admin").roles("CLIENT", "ADMIN"))
//                .withUser(User.withUsername("client").password("{noop}client").roles("CLIENT"));
//    }
}
