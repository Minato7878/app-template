package com.nulp.railway.dto;

import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

    private String id;
    private int status;
    private String error;
    private String message;
    private String path;
    private LocalDateTime timestamp;

    private static final String EXCEPTION_ID_PREFIX = "exception-";

    public static ApiError buildError(Exception e, HttpStatus status, HttpServletRequest request) {
        return ApiError.builder()
                .id(EXCEPTION_ID_PREFIX + UUID.randomUUID())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(e != null ? e.getMessage() : status.getReasonPhrase())
                .path(request.getServletPath())
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static ApiError buildValidationError(MethodArgumentNotValidException e, HttpStatus status, HttpServletRequest request) {
        String errorMessage = e.getBindingResult().getFieldErrors().stream()
                .map(error -> String.format("%s %s", StringUtils.capitalize(error.getField()), error.getDefaultMessage()))
                .collect(Collectors.joining("\n"));

        return ApiError.builder()
                .id(EXCEPTION_ID_PREFIX + UUID.randomUUID())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(errorMessage)
                .path(request.getServletPath())
                .timestamp(LocalDateTime.now())
                .build();
    }
}