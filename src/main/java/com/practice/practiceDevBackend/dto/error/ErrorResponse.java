package com.practice.practiceDevBackend.dto.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.Map;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private final int status;
    private final String message;
    private Map<String, String> errors; //K - поле, V - текст ошибки

    public ErrorResponse(int status, String message){
        this.status = status;
        this.message = message;
        this.errors = null;
    }


    public ErrorResponse(int status, String message, Map<String, String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
}
