package com.example.demo.openfeigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.openfeigns.configs.FptOcrFeignConfig;

/**
 * Feign client for FPT.AI OCR API.
 */
@FeignClient(name = "fpt-ocr", url = "${fpt.api.url}", configuration = FptOcrFeignConfig.class)
public interface FptOcrClient {
    @PostMapping(value = "/idr/vnm", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String recognizeId(
        @RequestHeader("api-key") String apiKey,
        @RequestPart("image") MultipartFile image
    );
}
