package com.tara.itinerary.exception;

import java.time.Instant;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private String details;
    private Instant timestamp;
    private String path;
    private int status;
}
