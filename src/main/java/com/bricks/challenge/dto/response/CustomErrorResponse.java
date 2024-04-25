package com.bricks.challenge.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class CustomErrorResponse {
        private LocalDateTime timestamp;
        private int status;
        private String error;
        private String message;

    public CustomErrorResponse(LocalDateTime timestamp, int status, String error, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
    }
}

