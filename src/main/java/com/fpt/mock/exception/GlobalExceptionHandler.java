package com.fpt.mock.exception;

import javax.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@EnableWebMvc
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @NonNull
    private final HttpServletRequest httpServletRequest;

    @Value("${spring.application.name}")
    private String service;


    @ExceptionHandler(GlobalRequestException.class)
    public ResponseEntity<Object> handleParentRequestException(GlobalRequestException requestException) {
        GlobalExceptionDto responseExceptionEntity = GlobalExceptionDto.builder()
            .api(httpServletRequest.getRequestURI())
            .method(httpServletRequest.getMethod())
            .port(httpServletRequest.getServerPort())
            .service(service)
            .message(requestException.getMessage())
            .error(requestException.getHttpStatus().name())
            .code(requestException.getHttpStatus().value())
            .build();

        return new ResponseEntity<>(responseExceptionEntity, requestException.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception exception) {
        GlobalExceptionDto responseExceptionEntity = GlobalExceptionDto.builder()
            .api(httpServletRequest.getRequestURI())
            .method(httpServletRequest.getMethod())
            .port(httpServletRequest.getServerPort())
            .service(service)
            .message(exception.getMessage())
            .error(HttpStatus.INTERNAL_SERVER_ERROR.name())
            .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .build();

        return new ResponseEntity<>(responseExceptionEntity, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @NonNull
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(@NonNull Exception exception,
                                                             @Nullable Object body,
                                                             @NonNull HttpHeaders httpHeaders,
                                                             @NonNull HttpStatus httpStatus,
                                                             @NonNull WebRequest webRequest) {
        GlobalExceptionDto responseExceptionEntity = GlobalExceptionDto.builder()
            .api(httpServletRequest.getRequestURI())
            .method(httpServletRequest.getMethod())
            .port(httpServletRequest.getServerPort())
            .service(service)
            .message(exception.getMessage())
            .error(httpStatus.name())
            .code(httpStatus.value())
            .build();

        return new ResponseEntity<>(responseExceptionEntity, httpStatus);
    }
}
