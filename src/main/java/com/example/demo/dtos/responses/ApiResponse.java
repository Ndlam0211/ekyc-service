package com.example.demo.dtos.responses;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Standard API response wrapper.
 */
public class ApiResponse<T> {
    private boolean success;
    private int code;
    private String message;
    private T data;
    private String timestamp;
    private String path;

    public ApiResponse() {
    }

    public ApiResponse(boolean success, int code, String message, T data, String path) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
        this.path = path;
        this.timestamp = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
                .format(LocalDateTime.now());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
