package com.example.secureautodeploymentplatform.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ApiResponse<T>{
    private T data;
    private String message;

    public ApiResponse(String message,T data) {
        this.data = data;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
