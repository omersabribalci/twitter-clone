package com.wtech.twitter.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TwitterErrorResponse {
    private int status;
    private String message;
    private Long timeStamp;
}
