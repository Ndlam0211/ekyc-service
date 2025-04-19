package com.example.demo;

import com.example.demo.EkycService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/ekyc")
public class EkycController {

    @Autowired
    private EkycService ekycService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadIdImages(
            @RequestParam("frontImage") MultipartFile frontImage,
            @RequestParam("backImage") MultipartFile backImage) {
        try {
            // Basic validation
            if (frontImage.isEmpty() || backImage.isEmpty()) {
                return ResponseEntity.badRequest().body("Front and back images are required.");
            }

            // Validate image size (5MB limit)
            final long maxFileSize = 5 * 1024 * 1024; // 5MB
            if (frontImage.getSize() > maxFileSize || backImage.getSize() > maxFileSize) {
                return ResponseEntity.badRequest().body("Image size exceeds the maximum limit of 5MB.");
            }

            // TODO: Add more specific validation for image types, size limits, etc.

            EkycService.EkycResult ocrResult = ekycService.performOcr(frontImage, backImage);
            return ResponseEntity.ok(ocrResult);

        } catch (InvalidParameterException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (CroppingFailedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IdNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (NoUrlException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (UrlOpenFailedException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
        } catch (InvalidImageFileException e) {
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(e.getMessage());
        } catch (BadDataException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (NoBase64Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (InvalidBase64Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IOException e) {
            System.err.println("IO Exception during eKYC processing: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("IO Error processing ID images: " + e.getMessage());
        } catch (EkycService.EkycException e) {
            // General EkycException or any other unexpected exception
            System.err.println("Error during eKYC processing: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing ID images: " + e.getMessage());
        }
    }
}
