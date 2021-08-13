package com.heqiao.admin.entity;

import lombok.Data;

import java.util.Map;

/**
 * @author heq3
 * @create 2021/7/7 18:05
 */
@Data
public class Response {
    private String message;
    private int code;
    private Object data;

    public Response(String message, int code, Object object) {
        this.message = message;
        this.code = code;
        this.data = object;
    }
}
