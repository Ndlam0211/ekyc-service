package com.example.demo.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.dtos.EkycResult;
import com.example.demo.dtos.FrontResult;
import com.example.demo.dtos.BackResult;
import com.example.demo.exceptions.EkycException;
import com.example.demo.openfeigns.FptOcrClient;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Executor;

@Service
public class EkycService {
    @Value("${fpt.api.key}")
    private String fptApiKey;
    private final FptOcrClient fptOcrClient;
    private final ObjectMapper objectMapper;
    private final Executor ekycExecutor;

    public EkycService(FptOcrClient fptOcrClient, ObjectMapper objectMapper,
            @Qualifier("ekycExecutor") Executor ekycExecutor) {
        this.fptOcrClient = fptOcrClient;
        this.objectMapper = objectMapper;
        this.ekycExecutor = ekycExecutor;
    }

    public EkycResult performOcr(MultipartFile frontImage, MultipartFile backImage) throws IOException {
        CompletableFuture<FrontResult> frontFuture = processFrontImageAsync(frontImage);
        CompletableFuture<BackResult> backFuture = processBackImageAsync(backImage);
        return collectOcrResults(frontFuture, backFuture);
    }

    private CompletableFuture<FrontResult> processFrontImageAsync(MultipartFile frontImage) {
        return CompletableFuture
                .supplyAsync(() -> recognizeId(frontImage), ekycExecutor)
                .thenApply(this::parseFrontResult)
                .exceptionally(this::handleFrontOcrException);
    }

    private CompletableFuture<BackResult> processBackImageAsync(MultipartFile backImage) {
        return CompletableFuture
                .supplyAsync(() -> recognizeId(backImage), ekycExecutor)
                .thenApply(this::parseBackResult)
                .exceptionally(this::handleBackOcrException);
    }

    private String recognizeId(MultipartFile image) {
        return fptOcrClient.recognizeId(fptApiKey, image);
    }

    private EkycResult collectOcrResults(CompletableFuture<FrontResult> frontFuture,
            CompletableFuture<BackResult> backFuture) {
        try {
            CompletableFuture.allOf(frontFuture, backFuture).join();
            EkycResult ekycResult = new EkycResult();
            ekycResult.setFrontIdData(frontFuture.join());
            ekycResult.setBackIdData(backFuture.join());
            return ekycResult;
        } catch (CompletionException ex) {
            if (ex.getCause() instanceof EkycException) {
                throw (EkycException) ex.getCause();
            }
            throw new EkycException("Unexpected OCR error", ex);
        }
    }

    // Parse the OCR result JSON into FrontResult and BackResult objects
    private FrontResult parseFrontResult(String json) {
        return parseOcrResult(json, FrontResult.class, "Failed to parse front OCR result");
    }

    private BackResult parseBackResult(String json) {
        return parseOcrResult(json, BackResult.class, "Failed to parse back OCR result");
    }

    private <T> T parseOcrResult(String json, Class<T> clazz, String errorMessage) {
        try {
            JsonNode data = objectMapper.readTree(json).path("data");
            return data.isArray() && data.size() > 0
                    ? objectMapper.treeToValue(data.get(0), clazz)
                    : null;
        } catch (IOException e) {
            throw new EkycException(errorMessage, e);
        }
    }

    // Handle exceptions for front and back OCR processing
    private FrontResult handleFrontOcrException(Throwable t) {
        throw unwrapOcrException(t, "OCR front failed: ");
    }

    private BackResult handleBackOcrException(Throwable t) {
        throw unwrapOcrException(t, "OCR back failed: ");
    }

    private EkycException unwrapOcrException(Throwable t, String prefix) {
        if (t.getCause() instanceof EkycException) {
            return (EkycException) t.getCause();
        }
        return new EkycException(prefix + t.getMessage());
    }
}
