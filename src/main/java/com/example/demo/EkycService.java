package com.example.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class EkycService {

    @Value("${fpt.api.key}")
    private String fptApiKey;

    @Value("${fpt.api.url}")
    private String fptApiUrl;

    @Autowired
    private FptOcrClient fptOcrClient;

    public EkycResult performOcr(MultipartFile frontImage, MultipartFile backImage) throws IOException {
        try {
            String response = fptOcrClient.recognizeId(fptApiKey, frontImage, backImage);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);

            int errorCode = root.path("errorCode").asInt();
            String errorMessage = root.path("errorMessage").asText();

            if (errorCode == 0) {
                JsonNode dataNode = root.path("data");
                // Assuming front image data is the first element and back image is the second, or adjust based on actual API behavior
                JsonNode frontData = dataNode.size() > 0 ? dataNode.get(0) : null;
                JsonNode backData = dataNode.size() > 1 ? dataNode.get(1) : null;

                EkycResult result = new EkycResult();
                result.setFrontIdData(frontData != null ? frontData.toString() : null);
                result.setBackIdData(backData != null ? backData.toString() : null);
                return result;
            } else {
                switch (errorCode) {
                    case 1:
                        throw new InvalidParameterException("Invalid Parameters or Values!" + errorMessage);
                    case 2:
                        throw new CroppingFailedException("Failed in cropping: " + errorMessage);
                    case 3:
                        throw new IdNotFoundException("Unable to find ID card in the image: " + errorMessage);
                    case 5:
                        throw new NoUrlException("No URL in the request: " + errorMessage);
                    case 6:
                        throw new UrlOpenFailedException("Failed to open the URL!: " + errorMessage);
                    case 7:
                        throw new InvalidImageFileException("Invalid image file: " + errorMessage);
                    case 8:
                        throw new BadDataException("Bad data: " + errorMessage);
                    case 9:
                        throw new NoBase64Exception("No string base64 in the request: " + errorMessage);
                    case 10:
                        throw new InvalidBase64Exception("String base64 is not valid: " + errorMessage);
                    default:
                        throw new EkycException("FPT.AI OCR API returned an error: " + errorCode + " - " + errorMessage);
                }
            }
        } catch (Exception e) {
            System.err.println("Error calling FPT.AI OCR API: " + e.getMessage());
            throw new EkycException("Error calling FPT.AI OCR API", e);
        }
    }

    // Custom exception classes
    public static class EkycException extends RuntimeException {
        public EkycException(String message) {
            super(message);
        }

        public EkycException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class InvalidParameterException extends EkycException {
        public InvalidParameterException(String message) {
            super(message);
        }
    }

    public static class CroppingFailedException extends EkycException {
        public CroppingFailedException(String message) {
            super(message);
        }
    }

    public static class IdNotFoundException extends EkycException {
        public IdNotFoundException(String message) {
            super(message);
        }
    }

    public static class NoUrlException extends EkycException {
        public NoUrlException(String message) {
            super(message);
        }
    }

    public static class UrlOpenFailedException extends EkycException {
        public UrlOpenFailedException(String message) {
            super(message);
        }
    }

    public static class InvalidImageFileException extends EkycException {
        public InvalidImageFileException(String message) {
            super(message);
        }
    }

    public static class BadDataException extends EkycException {
        public BadDataException(String message) {
            super(message);
        }
    }

    public static class NoBase64Exception extends EkycException {
        public NoBase64Exception(String message) {
            super(message);
        }
    }

    public static class InvalidBase64Exception extends EkycException {
        public InvalidBase64Exception(String message) {
            super(message);
        }
    }

    // Data class to hold the results
    public static class EkycResult {
        private String frontIdData;
        private String backIdData;

        public String getFrontIdData() {
            return frontIdData;
        }

        public void setFrontIdData(String frontIdData) {
            this.frontIdData = frontIdData;
        }

        public String getBackIdData() {
            return backIdData;
        }

        public void setBackIdData(String backIdData) {
            this.backIdData = backIdData;
        }
    }
}
