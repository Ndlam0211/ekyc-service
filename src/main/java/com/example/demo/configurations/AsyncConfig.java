package com.example.demo.configurations;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Configuration for async executor used in eKYC OCR processing.
 */
@Configuration
@EnableAsync
public class AsyncConfig {
    @Bean("ekycExecutor")
    public Executor ekycExecutor() {
        ThreadPoolTaskExecutor exec = new ThreadPoolTaskExecutor();
        exec.setCorePoolSize(5);
        exec.setMaxPoolSize(10);
        exec.setQueueCapacity(50);
        exec.setThreadNamePrefix("ekyc-");
        exec.initialize();
        return exec;
    }
}
