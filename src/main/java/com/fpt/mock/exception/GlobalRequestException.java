package com.fpt.mock.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class GlobalRequestException extends RuntimeException {

    private final HttpStatus httpStatus;

    public GlobalRequestException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
