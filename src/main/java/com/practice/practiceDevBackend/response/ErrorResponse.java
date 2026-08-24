package com.practice.practiceDevBackend.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private final int status;
    private final String message;
    private Map<String, String> errors;

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
