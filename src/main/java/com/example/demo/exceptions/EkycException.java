package com.example.demo.exceptions;

/**
 * Custom exception for eKYC processing errors.
 */
public class EkycException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EkycException(String message) {
        super(message);
    }

    public EkycException(String message, Throwable cause) {
        super(message, cause);
    }

    public EkycException(Throwable cause) {
        super(cause);
    }
}
