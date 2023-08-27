package com.iny.opensoftware.presentation;

import com.fasterxml.jackson.annotation.JsonProperty;  
import lombok.Data;  
import org.springframework.http.HttpStatus;  
  
@Data  
public class ApiResponse<D> {  
    @JsonProperty("code")  
    private String code;  
    @JsonProperty("status")  
    private Integer status;  
    @JsonProperty("status_name")  
    private String statusName;  
    @JsonProperty("message")  
    private String message;  
    @JsonProperty("payload")  
    private D payload;  
  
    public static <D> ApiResponse<D> of(HttpStatus status, String code, String message, D payload) {  
        ApiResponse<D> res = new ApiResponse<>();  
        res.setStatus(status.value());  
        res.setStatusName(status.name());  
        res.setCode(code);  
        res.setMessage(message);  
        res.setPayload(payload);  
        return res;  
    }  
  
    public static <D> ApiResponse<D> of(D payload) {  
        return ApiResponse.of(HttpStatus.OK, "0", "ok", payload);  
    }  
}

