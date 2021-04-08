package com.example.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(value = {
        "com.example.core",
        "com.example.common"}
)
@EnableTransactionManagement
public class ServiceConfiguration {
}
