package com.wtech.twitter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private int status;
    private Long timeStamp;


    public static <T>ApiResponse<T> success(String message, T data, int status) {

        return new ApiResponse<>(true, message, data, status, System.currentTimeMillis());
    }

    public static <T>ApiResponse<T> error(String message,int status) {
        return new ApiResponse<>(false, message, null, status, System.currentTimeMillis());
    }

}
