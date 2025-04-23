package com.example.demo.controllers;

import com.example.demo.dtos.EkycResult;
import com.example.demo.dtos.responses.ApiResponse;
import com.example.demo.exceptions.EkycException;
import com.example.demo.services.EkycService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@RestController
@RequestMapping("/ekyc")
public class EkycController {
        private final EkycService ekycService;
        private final HttpServletRequest request;

        public EkycController(EkycService ekycService, HttpServletRequest request) {
                this.ekycService = ekycService;
                this.request = request;
        }

        @PostMapping("/upload")
        public ResponseEntity<ApiResponse<?>> uploadIdImages(
                        @RequestParam("frontImage") MultipartFile frontImage,
                        @RequestParam("backImage") MultipartFile backImage) {
                try {
                        String validationError = validateImages(frontImage, backImage);
                        if (validationError != null) {
                                return buildErrorResponse(HttpStatus.BAD_REQUEST, validationError);
                        }
                        EkycResult ocrResult = ekycService.performOcr(frontImage, backImage);
                        return buildSuccessResponse(ocrResult);
                } catch (IOException e) {
                        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                                        "IO Error processing ID images: " + e.getMessage());
                } catch (EkycException e) {
                        return buildErrorResponse(HttpStatus.BAD_REQUEST,
                                        "Error during eKYC processing: " + e.getMessage());
                }
        }

        private String validateImages(MultipartFile frontImage, MultipartFile backImage) {
                if (frontImage == null || backImage == null || frontImage.isEmpty() || backImage.isEmpty()) {
                        return "Front and back images are required.";
                }
                final long maxFileSize = 5 * 1024 * 1024; // 5MB
                if (frontImage.getSize() > maxFileSize || backImage.getSize() > maxFileSize) {
                        return "Image size exceeds the maximum limit of 5MB.";
                }
                return null;
        }

        private ResponseEntity<ApiResponse<?>> buildErrorResponse(HttpStatus status, String message) {
                return ResponseEntity.status(status)
                                .body(new ApiResponse<>(false, status.value(), message, null, request.getRequestURI()));
        }

        private ResponseEntity<ApiResponse<?>> buildSuccessResponse(EkycResult result) {
                return ResponseEntity.ok(new ApiResponse<>(true, HttpStatus.OK.value(),
                                "OCR completed successfully", result, request.getRequestURI()));
        }
}
