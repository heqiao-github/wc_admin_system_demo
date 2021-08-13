package com.heqiao.admin.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author heq3
 * @create 2021/8/10 18:39
 */
@Data
public class Status implements Serializable {
    int code;
    String message;

    public Status() {

    }
    public Status(int code , String message) {
        this.code = code;
        this.message = message;
    }
}
