package com.edanur.demo.configuration;


import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringConfig {
    @Bean
    public DozerBeanMapper getMapper() {
        return new DozerBeanMapper();
    }
}
