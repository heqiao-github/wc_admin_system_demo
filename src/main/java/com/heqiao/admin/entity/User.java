package com.heqiao.admin.entity;

import lombok.Data;

import java.time.Instant;

/**
 * @author heq3
 * @create 2021/7/7 17:29
 */
@Data
public class User {
    private String username;
    private String userId;
    private String password;
    private String phone;
    private String email;
    private String role_id;
    private Instant createTime;
}
