package com.example.demo.openfeigns.errordecoders;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * Custom Feign error decoder for FPT.AI OCR API.
 */
public class FptOcrErrorDecoder implements ErrorDecoder {
    @Autowired
    private ObjectMapper mapper;

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            String body = new String(response.body().asInputStream().readAllBytes());
            JsonNode node = mapper.readTree(body);
            String msg = node.get("errorMessage").asText();
            return new com.example.demo.exceptions.EkycException(msg);
        } catch (IOException e) {
            return new com.example.demo.exceptions.EkycException("Unknown error calling FPT: " + e.getMessage());
        }
    }
}
