package com.example.VHS.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PriceException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(PriceException.class);

    public PriceException(String message) {
        super(message);
        logger.warn(message);

    }

    public PriceException(String message, Throwable cause) {
        super(message, cause);
        logger.warn(message);

    }
}
