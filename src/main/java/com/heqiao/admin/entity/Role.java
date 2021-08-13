package com.heqiao.admin.entity;

import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * @author heq3
 * @create 2021/7/20 14:43
 */
@Data
public class Role {
    private String name;
    private String _id;
    private Instant createTime;
    private Instant authTime;
    private String authName;
    private List<String>menus;
}
