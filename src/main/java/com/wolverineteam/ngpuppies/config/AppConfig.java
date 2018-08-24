package com.wolverineteam.ngpuppies.config;

import com.wolverineteam.ngpuppies.models.*;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    public SessionFactory createSessionFactory() {
        return new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Bill.class)
                .addAnnotatedClass(Currency.class)
                .addAnnotatedClass(Role.class)
                .addAnnotatedClass(Service.class)
                .addAnnotatedClass(Subscriber.class)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
