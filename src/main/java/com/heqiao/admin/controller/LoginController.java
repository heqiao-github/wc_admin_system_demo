package com.heqiao.admin.controller;

import com.heqiao.admin.entity.*;
import com.heqiao.admin.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;


/**
 * @author heq3
 * @create 2021/7/7 17:26
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class LoginController {
    private static List<Role> roles = new ArrayList<>();
    private static List<User> users = new ArrayList<>();

    @Autowired
    RedisUtil redisUtil;

    static {
        User user = new User();
        user.setUsername("admin");
        user.setRole_id("123456");
        user.setPassword("admin");
        user.setPhone("13872946955");
        user.setCreateTime(Instant.now());
        user.setEmail("184572023@qq.com");
        user.setUserId(UUID.randomUUID().toString());
        users.add(user);
        Role role = new Role();
        role.setName("admin超级管理员");
        role.set_id("123456");
        role.setAuthTime(Instant.now());
        role.setCreateTime(Instant.now());
        role.setAuthName("admin");
        List<String> list = new ArrayList<>();
        list.add("/userCenter");
        list.add("/user");
        list.add("/role");
        list.add("/me");
        list.add("/");
        role.setMenus(list);
        roles.add(role);
    }

    @ResponseBody
    @PostMapping(value = "/login")
    public Response login(@RequestBody User user) {
        log.info(user.toString());
        for (User user1 : users) {
            if (user1.getUsername().equals(user.getUsername())) {
                if (user1.getPassword().equals(user.getPassword())) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("user", user1);
                    map.put("roles", roles);
                    return new Response("成功登录", 100, map);
                } else {
                    return new Response("密码错误", -1, null);
                }
            }
        }
        return new Response("用户名不存在，登录失败", -1, null);
    }

    /**
     * 获取用户列表
     *
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/list")
    public Response list() {
        Map<String, Object> map = new HashMap<>();
        map.put("roles", roles);
        map.put("users", users);
        return new Response("成功登录", 100, map);
    }

    @ResponseBody
    @GetMapping(value = "/getRoleList")
    public Response getRoleList() {
        return new Response("huoqu", 100, roles);
    }

    @ResponseBody
    @PostMapping(value = "/roleAdd")
    public Response roleAdd(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        Role role = new Role();
        role.set_id(UUID.randomUUID().toString());
        role.setName(String.valueOf(map.get("roleName")));
        role.setAuthName("胡歌");
        role.setAuthTime(Instant.now());
        role.setCreateTime(Instant.now());
        roles.add(role);
        return new Response("成功登录", 100, roles);
    }

    @ResponseBody
    @PostMapping(value = "/userRole")
    public Response userRole(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        for (Role role1 : roles) {
            if ("123456".equals(map.get("_id"))) {
                return new Response("超级管理员无法修改权限", -1, null);
            } else if(role1.get_id().equals(map.get("_id"))) {
                role1.setAuthName(map.get("authName").toString());
                role1.setMenus((List<String>) map.get("menus"));
                return new Response("xiugai", 100, role1);
            }
        }
        return new Response("成功登录", 100, roles);
    }

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/userAdd")
    public Response userAdd(@RequestBody User user) {
        user.setCreateTime(Instant.now());
        user.setUserId(UUID.randomUUID().toString());
        user.setUserId(UUID.randomUUID().toString());
        users.add(user);
        return new Response("成功登录", 100, roles);
    }

    @ResponseBody
    @PostMapping(value = "/userUpdate")
    public Response userUpdate(@RequestBody User user) {
        for (User user1 : users) {
            if (user1.getUserId().equals(user.getUserId())) {
                user1.setEmail(user.getEmail());
                user1.setPhone(user.getPhone());
                user1.setPassword(user.getPassword());
                user1.setUsername(user.getUsername());
                user1.setRole_id(user.getRole_id());
                return new Response("xiugai成功", 100, roles);
            }
        }
        return new Response("修改失败", -1, null);
    }

    @ResponseBody
    @PostMapping(value = "/deleteUser")
    public Response deleteUser(@RequestBody Map<String, String> map) {
        String userId = map.get("userId");
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                users.remove(user);
                return new Response("删除车工", 100, null);
            }
        }
        return new Response("成功登录", 100, roles);
    }

    @ResponseBody
    @GetMapping(value = "/activeApply")
    public Response activeApply() {
        System.out.println(11);
        Status status1 = null;
        Status status = (Status) redisUtil.get("status");
        if(status.getCode()== 0) {
            status1 = new Status(StatusEnum.STATUS3.getCode(), StatusEnum.STATUS3.getMessage());
            redisUtil.set("status",status1);
        }
        if(status.getCode() == 2) {
            status1 = new Status(StatusEnum.STATUS5.getCode(), StatusEnum.STATUS5.getMessage());
            redisUtil.set("status",status1);
        }
        if(status.getCode() == 3) {
            status1 = new Status(StatusEnum.STATUS6.getCode(), StatusEnum.STATUS6.getMessage());
            redisUtil.set("status",status1);
        }
        if(status.getCode() == 3) {
            status1 = new Status(StatusEnum.STATUS6.getCode(), StatusEnum.STATUS6.getMessage());
            redisUtil.set("status",status1);
        }
        return new Response("审核成功", 100, null);
    }


    @ResponseBody
    @GetMapping(value = "/getTableInfo")
    public Response getData() {
        Map<String,Object> apply =(Map<String,Object>) redisUtil.get("apply");
        Map<String,Object> total = new HashMap<>(3);
        Map<String,Object> unCheck = new HashMap<>();
        Map<String,Object> apply1 =(Map<String,Object>) apply.get("apply");
        unCheck.put("name",apply1.get("name"));
        unCheck.put("age",30);
        unCheck.put("tel",apply1.get("tel"));
        unCheck.put("idcard",apply1.get("idcard"));
        unCheck.put("bankcard",apply1.get("bankcard"));
        unCheck.put("bankname",apply1.get("bankname"));
        Map<String,Object> apply2 =(Map<String,Object>) apply.get("patient");
        unCheck.put("patientName",apply2.get("name"));
        unCheck.put("patientPhone",apply2.get("patientPhone"));
        total.put("unCheck",unCheck);
        return new Response("获取信息登录", 100, total);
    }

    

}
