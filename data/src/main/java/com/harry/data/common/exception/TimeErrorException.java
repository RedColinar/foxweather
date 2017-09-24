package com.harry.data.common.exception;


import com.harry.data.common.reactive.ErrorResponse;

public class TimeErrorException extends RuntimeException {

    private ErrorResponse errorResponse;

    public TimeErrorException() {
    }

    public TimeErrorException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }
}
