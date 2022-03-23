package com.fpt.mock.exception;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GlobalExceptionDto {

    private String api;
    private String method;
    private int port;
    private String service;
    private String message;
    private String error;
    private Integer code;
    @Builder.Default
    private String timestamp = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyy-MM-dd hh:mm:ss a z Z"));

}
