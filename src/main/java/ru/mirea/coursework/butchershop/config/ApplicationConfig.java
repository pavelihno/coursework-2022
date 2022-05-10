package ru.mirea.coursework.butchershop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


@Configuration
@EnableJpaRepositories("ru.mirea.coursework.butchershop.repos")
@EnableGlobalMethodSecurity(jsr250Enabled=true)
@ComponentScan
public class ApplicationConfig {

}
