package com.clientlist.config;

import com.clientlist.utils.LoggingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfig {
    @Bean
    public LoggingFilter loggingFilter() {
        return new LoggingFilter();
    }
}

