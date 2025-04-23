package com.example.demo.openfeigns.configs;

import org.springframework.context.annotation.Bean;

import com.example.demo.openfeigns.errordecoders.FptOcrErrorDecoder;

import feign.codec.ErrorDecoder;

/**
 * Feign configuration for FPT OCR client.
 */
public class FptOcrFeignConfig {
    @Bean
    public ErrorDecoder fptOcrErrorDecoder() {
        return new FptOcrErrorDecoder();
    }
}
