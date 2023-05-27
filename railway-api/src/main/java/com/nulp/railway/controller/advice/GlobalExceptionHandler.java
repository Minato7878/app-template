package com.nulp.railway.controller.advice;

import com.nulp.railway.dto.ApiError;
import com.nulp.railway.exception.BadRequestException;
import com.nulp.railway.exception.CustomAuthException;
import com.nulp.railway.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleException(BadRequestException e, HttpServletRequest request) {
        var response = ApiError.buildError(e, HttpStatus.BAD_REQUEST, request);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleException(EntityNotFoundException e, HttpServletRequest request) {
        var response = ApiError.buildError(e, HttpStatus.BAD_REQUEST, request);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleException(AuthenticationException e, HttpServletRequest request) {
        var response = ApiError.buildError(e, HttpStatus.BAD_REQUEST, request);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({CustomAuthException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleException(CustomAuthException e, HttpServletRequest request) {
        var response = ApiError.buildError(e, HttpStatus.BAD_REQUEST, request);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleException(MethodArgumentNotValidException e, HttpServletRequest request) {
        var response = ApiError.buildValidationError(e, HttpStatus.BAD_REQUEST, request);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
