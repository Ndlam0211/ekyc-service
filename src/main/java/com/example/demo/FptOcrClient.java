package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "fpt-ocr", url = "${fpt.api.url}")
public interface FptOcrClient {

    @PostMapping(value = "/idr/vnm", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String recognizeId(
            @RequestHeader("api-key") String apiKey,
            @RequestPart("image1") MultipartFile image1,
            @RequestPart("image2") MultipartFile image2
    );
}
